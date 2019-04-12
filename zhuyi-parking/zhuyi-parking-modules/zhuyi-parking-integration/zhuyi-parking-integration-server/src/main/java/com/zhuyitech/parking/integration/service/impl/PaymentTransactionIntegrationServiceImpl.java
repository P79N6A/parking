package com.zhuyitech.parking.integration.service.impl;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zhuyitech.parking.common.exception.BusinessException;
import com.zhuyitech.parking.integration.trade.PaymentTransactionIntegrationService;
import com.zhuyitech.parking.pms.dto.request.transaction.CompletePaymentUserPayOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceAddRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.CompletePaymentRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.CustomerPayOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.result.UserPayOrderResultDto;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zhuyitech.parking.ucc.service.api.UserPayOrderService;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.CustomerPaySuccessPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 支付交易集成服务
 *
 * @author walkman
 */
@Service("paymentTransactionIntegrationService")
@Slf4j
public class PaymentTransactionIntegrationServiceImpl implements PaymentTransactionIntegrationService {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private UserPayOrderService userPayOrderService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultDto processPaySuccess(CustomerPaySuccessPayload customerPaySuccessPayload) {
        ResultDto resultDto = new ResultDto();
        log.debug("------[接收到要处理的用户支付订单{}]--------[开始处理时间{}]------",
                JSON.toJSONString(customerPaySuccessPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        //根据消息获取支付订单信息
        CustomerPayOrderGetRequestDto customerPayOrderGetRequestDto = new CustomerPayOrderGetRequestDto();
        customerPayOrderGetRequestDto.setCustomerUserId(customerPaySuccessPayload.getCustomerUserId());
        customerPayOrderGetRequestDto.setBizOrderNo(customerPaySuccessPayload.getBizOrderNo());
        customerPayOrderGetRequestDto.setBizType(customerPaySuccessPayload.getBizOrderType());
        customerPayOrderGetRequestDto.setOrderNo(customerPaySuccessPayload.getPayOrderNo());
        ObjectResultDto<UserPayOrderResultDto> payOrderResultDto = userPayOrderService.getCustomerPayOrder(customerPayOrderGetRequestDto);
        if (payOrderResultDto.isSuccess() && payOrderResultDto.getData() != null) {
            UserPayOrderResultDto userPayOrderResultDto = payOrderResultDto.getData();
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getUserPayOrderLockKey(userPayOrderResultDto.getOrderNo()));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME * 3L);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //如果处理成功，则不再处理
                    if (PayStatusEnum.PAY_SUCCESS.getValue().equals(payOrderResultDto.getCode())) {
                        return resultDto.success();
                    }
                    if (BizTypeEnum.RECHARGE.getValue().equals(customerPaySuccessPayload.getBizOrderType())) {
                        //处理充值支付成功通知消息
                        resultDto = processRechargePaySuccess(customerPaySuccessPayload);
                    } else if (BizTypeEnum.PAYMENT.getValue().equals(customerPaySuccessPayload.getBizOrderType())) {
                        //处理订单支付成功通知消息
                        resultDto = processParkingOrderPaySuccess(customerPaySuccessPayload);
                    } else if (BizTypeEnum.APPOINTMENT.getValue().equals(customerPaySuccessPayload.getBizOrderType())) {
                        //处理预约支付成功通知消息
                        resultDto = processAppointOrderPaySuccess(customerPaySuccessPayload);
                    }
                }
            } catch (Exception e) {
                log.error("用户支付成功通知消息处理失败", e.getMessage());
                throw new BusinessException("用户支付成功通知消息处理失败", e.getMessage());
            }
        }
        log.debug("------[接收到要处理的用户订单{}]--------[结束处理时间{}]------", JSON.toJSONString(customerPaySuccessPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        return resultDto;
    }

    /**
     * 处理订单支付成功通知
     *
     * @param customerPaySuccessPayload
     * @return
     */
    private ResultDto processParkingOrderPaySuccess(CustomerPaySuccessPayload customerPaySuccessPayload) throws BusinessException {
        ResultDto resultDto = new ResultDto();
        try {

            log.debug("------completeSuccessPaymentRechargeOrder[订单{}完成支付修改userPayOrder记录开始时间{}]------",
                    customerPaySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            CompletePaymentUserPayOrderRequestDto completePaymentUserPayOrderRequestDto = new CompletePaymentUserPayOrderRequestDto();
            completePaymentUserPayOrderRequestDto.setUserId(customerPaySuccessPayload.getCustomerUserId());
            completePaymentUserPayOrderRequestDto.setOrderNo(customerPaySuccessPayload.getBizOrderNo());
            completePaymentUserPayOrderRequestDto.setPayOrderNo(customerPaySuccessPayload.getPayOrderNo());
            completePaymentUserPayOrderRequestDto.setSucceedPayTime(customerPaySuccessPayload.getSucceedPayTime());
            //订单支付金额
            if (customerPaySuccessPayload.getActualAmount() != null) {
                completePaymentUserPayOrderRequestDto.setActualAmount(NumberUtils.amountFen2Yuan(customerPaySuccessPayload.getActualAmount()));
            } else {
                completePaymentUserPayOrderRequestDto.setActualAmount(BigDecimal.ZERO);
            }
            completePaymentUserPayOrderRequestDto.setTransactionNo(customerPaySuccessPayload.getTransactionNo());
            accountTransactionService.completeSuccessPaymentUserPayOrder(null, completePaymentUserPayOrderRequestDto);
            log.debug("------completeSuccessPaymentRechargeOrder[订单{}完成支付修改userPayOrder记录结束时间{}]------", customerPaySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        } catch (Exception e) {
            log.error("用户订单处理失败" + e.getMessage(), e);
            throw new BusinessException("用户订单处理失败", e.getMessage());
        }
        return resultDto;
    }

    /**
     * 处理充值支付成功通知
     *
     * @param paySuccessPayload
     * @return
     */
    private ResultDto processRechargePaySuccess(CustomerPaySuccessPayload paySuccessPayload) {
        ResultDto resultDto = new ResultDto();
        try {
            if (paySuccessPayload != null) {
                log.debug("------[接收到用户支付成功订单{}]--------[开始处理时间{}]------", JSON.toJSONString(paySuccessPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                BigDecimal amount = BigDecimal.ZERO;
                if (paySuccessPayload.getActualAmount() != null) {
                    amount = NumberUtils.amountFen2Yuan(paySuccessPayload.getActualAmount());
                }
                log.debug("------completeSuccessPaymentRechargeOrder[订单{}完成支付修改充值记录开始时间{}]------", paySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                CompletePaymentRechargeOrderRequestDto completeRechargePaymentRequestDto = new CompletePaymentRechargeOrderRequestDto();
                completeRechargePaymentRequestDto.setOrderNo(paySuccessPayload.getBizOrderNo());
                completeRechargePaymentRequestDto.setPayOrderNo(paySuccessPayload.getPayOrderNo());
                completeRechargePaymentRequestDto.setActualAmount(amount);
                completeRechargePaymentRequestDto.setSucceedPayTime(paySuccessPayload.getSucceedPayTime());
                completeRechargePaymentRequestDto.setRechargeType(paySuccessPayload.getPayWay());
                completeRechargePaymentRequestDto.setTransactionNo(paySuccessPayload.getTransactionNo());
                accountTransactionService.completeSuccessPaymentRechargeOrder(null, completeRechargePaymentRequestDto);
                log.debug("------completeSuccessPaymentRechargeOrder[订单{}完成支付修改充值记录开始时间{}]------", paySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                log.debug("------completeSuccessPaymentOrder[订单{}完成支付修改账户金额开始时间{}]------", paySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                AccountBalanceAddRequestDto accountBalanceAddRequestDto = new AccountBalanceAddRequestDto();
                accountBalanceAddRequestDto.setUserId(paySuccessPayload.getCustomerUserId());
                accountBalanceAddRequestDto.setAmount(amount);
                accountBalanceAddRequestDto.setBizType(paySuccessPayload.getBizOrderType());
                accountBalanceAddRequestDto.setBizNo(paySuccessPayload.getBizOrderNo());
                accountBalanceAddRequestDto.setTransactionNo(paySuccessPayload.getTransactionNo());
                accountBalanceAddRequestDto.setRemark(paySuccessPayload.getRemark());
                accountTransactionService.creditToAccountTcc(null, accountBalanceAddRequestDto);
                log.debug("------completeSuccessPaymentOrder[订单{}完成支付修改账户金额结束时间{}]------", paySuccessPayload.getPayOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                log.debug("------[接收到用户充值成功订单{}]--------[结束处理时间{}]------", JSON.toJSONString(paySuccessPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            }
        } catch (Exception e) {
            log.error("用户充值成功订单处理失败" + e.getMessage(), e);
            //放入处理失败消息队列
            RocketMessage<CustomerPaySuccessPayload> messageFail = new RocketMessage<>();
            messageFail.setDestination("zoeeasy_app_pay_message_fail");
            messageFail.setSender("INTEGRATION");
            messageFail.setMessageId(StringUtils.getUUID());
            messageFail.setPayload(paySuccessPayload);
            messageSendService.sendAndSaveSync(messageFail);
        }
        return resultDto;
    }

    /**
     * 处理预约订单支付成功通知
     *
     * @param customerPaySuccessPayload
     * @return
     */
    private ResultDto processAppointOrderPaySuccess(CustomerPaySuccessPayload customerPaySuccessPayload) {
        ResultDto resultDto = new ResultDto();
        return resultDto;
    }

    /**
     * 获取用户支付订单分布式锁键
     *
     * @param payOrderNo payOrderNo
     * @return
     */
    private String getUserPayOrderLockKey(String payOrderNo) {
        return new StringBuilder().append("lock:user.pay.order.").append(payOrderNo).toString();
    }

}
