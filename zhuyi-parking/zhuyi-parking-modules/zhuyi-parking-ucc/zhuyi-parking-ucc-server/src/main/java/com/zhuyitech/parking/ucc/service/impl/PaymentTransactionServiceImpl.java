package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.common.utils.*;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zhuyitech.parking.common.enums.RechargeStatusEnum;
import com.zhuyitech.parking.common.utils.SequenceUtils;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceAvailableCheckRequestDto;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceSubtractRequestDto;
import com.zhuyitech.parking.ucc.dto.request.recharge.UserRechargeRecordGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.ParkingAppointmentOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserPlaceRechargeOrderRequestDto;
import com.zhuyitech.parking.ucc.dto.result.AccountBalanceAvailableCheckResultDto;
import com.zhuyitech.parking.ucc.dto.result.UserRechargeRecordResultDto;
import com.zhuyitech.parking.ucc.enums.UCenterResultEnum;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.service.api.UserTransactionService;
import com.zhuyitech.parking.ucc.trade.PaymentTransactionService;
import com.zhuyitech.parking.ucc.trade.dto.*;
import com.zhuyitech.parking.ucc.user.dto.UserTradePasswordCheckRequestDto;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.order.appoint.PlatformAppointOrderService;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.transaction.ParkingOrderTransactionService;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentAppointmentOrderRequestDto;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.dto.request.order.AppointOrderPayRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 交易服务(充值，付款)
 *
 * @author walkman
 * @date 2018-01-11
 */
@Service("paymentTransactionService")
@Slf4j
public class PaymentTransactionServiceImpl implements PaymentTransactionService {

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private UserTransactionService userTransactionService;

    @Autowired
    private ParkingOrderTransactionService parkingOrderTransactionService;

    @Autowired
    private PlatformAppointOrderService platformAppointOrderService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    /**
     * 客户充值
     *
     * @param
     * @returns
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<RechargePlaceResultDto> recharge(RechargePlaceRequestDto requestDto) {
        ObjectResultDto<RechargePlaceResultDto> resultDto = new ObjectResultDto<>();

        PayWayEnum payWay = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWay == null) {
            return resultDto.makeResult(UCenterResultEnum.PAY_RECHARGE_WAY_ERROR.getValue(), UCenterResultEnum.PAY_RECHARGE_WAY_ERROR.getComment()
            );
        } else if (!(payWay.getValue().equals(PayWayEnum.ALIPAY.getValue())
                || payWay.getValue().equals(PayWayEnum.WEIXINPAY.getValue()))) {
            return resultDto.makeResult(UCenterResultEnum.PAY_WAY_NOT_SUPPORT.getValue(),
                    UCenterResultEnum.PAY_WAY_NOT_SUPPORT.getComment()
            );
        }
        if (requestDto.getChargeAmount() == null ||
                requestDto.getChargeAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return resultDto.makeResult(UCenterResultEnum.PAY_RECHARGE_AMOUNT_ERROR.getValue(), UCenterResultEnum.PAY_RECHARGE_AMOUNT_ERROR.getComment()
            );
        }
        try {

            RechargePlaceResultDto rechargeResultDto = new RechargePlaceResultDto();
            Long userId = requestDto.getSessionIdentity().getUserId();
            //创建值记录
            Date now = new Date();
            //充值订单号
            String rechargeOrderNo = SequenceUtils.generateRechargeOrderNo();
            //支付下单
            PlacePaymentOrderRequestDto placePaymentOrderRequestDto = new PlacePaymentOrderRequestDto();
            placePaymentOrderRequestDto.setPayerUserId(userId);
            placePaymentOrderRequestDto.setBizOrderType(BizTypeEnum.RECHARGE.getValue());
            placePaymentOrderRequestDto.setBizOrderNo(rechargeOrderNo);
            placePaymentOrderRequestDto.setPayWay(requestDto.getPayWay());
            if (payWay.getValue().equals(PayWayEnum.ALIPAY.getValue())) {
                placePaymentOrderRequestDto.setPayType(PayTypeEnum.ALI_APP.getValue());
            } else if (payWay.getValue().equals(PayWayEnum.WEIXINPAY.getValue())) {
                placePaymentOrderRequestDto.setPayType(PayTypeEnum.WX_APP.getValue());
            }
            placePaymentOrderRequestDto.setProductName("充值");
            placePaymentOrderRequestDto.setOrderDate(now);
            placePaymentOrderRequestDto.setOrderTime(now);
            //金额转换
            Integer orderAmount = NumberUtils.amountYuan2Fen(requestDto.getChargeAmount()).intValue();
            placePaymentOrderRequestDto.setOrderAmount(orderAmount);
            placePaymentOrderRequestDto.setRemark("充值");
            placePaymentOrderRequestDto.setOrderIp(requestDto.getSpbillCreateIp());
            placePaymentOrderRequestDto.setOrderPeriod(15);
            ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrderResultDto = tradePaymentManagerService.placePaymentOrder(placePaymentOrderRequestDto);
            if (placePaymentOrderResultDto.isFailed() || placePaymentOrderResultDto.getData() == null) {
                return resultDto.makeResult(UCenterResultEnum.PAY_RECHARGE_FAILED.getValue(),
                        UCenterResultEnum.PAY_RECHARGE_FAILED.getComment()
                );
            }
            PlacePaymentOrderResultDto placePaymentOrder = placePaymentOrderResultDto.getData();

            //用户充值下单
            UserPlaceRechargeOrderRequestDto userPlaceRechargeOrderRequestDto = new UserPlaceRechargeOrderRequestDto();
            userPlaceRechargeOrderRequestDto.setOrderUuid(StringUtils.getUUID());
            userPlaceRechargeOrderRequestDto.setChargeAmount(requestDto.getChargeAmount());
            userPlaceRechargeOrderRequestDto.setPayWay(requestDto.getPayWay());
            userPlaceRechargeOrderRequestDto.setRechargeOrderNo(rechargeOrderNo);
            userPlaceRechargeOrderRequestDto.setPayOrderNo(placePaymentOrder.getPayOrderNo());
            userPlaceRechargeOrderRequestDto.setSessionIdentity(requestDto.getSessionIdentity());

            ResultDto placeOrderResult = accountTransactionService.placeRechargeOrderPayment(userPlaceRechargeOrderRequestDto);
            if (placeOrderResult.isFailed()) {
                return resultDto.makeResult(UCenterResultEnum.PAY_RECHARGE_FAILED.getValue(),
                        UCenterResultEnum.PAY_RECHARGE_FAILED.getComment()
                );
            }
            rechargeResultDto.setAlipayOrderInfo(placePaymentOrder.getAlipayOrderInfo());
            rechargeResultDto.setAppId(placePaymentOrder.getAppId());
            rechargeResultDto.setNonceStr(placePaymentOrder.getNonceStr());
            rechargeResultDto.setPackages(placePaymentOrder.getPackages());
            rechargeResultDto.setPartnerId(placePaymentOrder.getPartnerId());
            rechargeResultDto.setPrepayId(placePaymentOrder.getPrepayId());
            rechargeResultDto.setPaySign(placePaymentOrder.getPaySign());
            rechargeResultDto.setSignType(placePaymentOrder.getSignType());
            rechargeResultDto.setOrderNo(rechargeOrderNo);
            rechargeResultDto.setTimeStamp(placePaymentOrder.getTimeStamp());
            rechargeResultDto.setTradeType(placePaymentOrder.getTradeType());
            resultDto.setData(rechargeResultDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("充值订单创建失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 充值确认
     *
     * @param requestDto 请求参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<RechargeConfirmResultDto> rechargeConfirm(RechargeConfirmRequestDto requestDto) {

        ObjectResultDto<RechargeConfirmResultDto> resultDto = new ObjectResultDto<>();

        try {

            UserRechargeRecordGetRequestDto rechargeRecordGetRequestDto = new UserRechargeRecordGetRequestDto();
            rechargeRecordGetRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
            rechargeRecordGetRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<UserRechargeRecordResultDto> userRechargeResultDto = userTransactionService.getUserRecharge(rechargeRecordGetRequestDto);
            if (userRechargeResultDto.isFailed() || userRechargeResultDto.getData() == null) {
                return resultDto.makeResult(UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getValue(), UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getComment()
                );
            }
            UserRechargeRecordResultDto userRecharge = userRechargeResultDto.getData();
            if (userRecharge.getRechargeAmount().compareTo(requestDto.getChargeAmount()) != 0) {
                return resultDto.makeResult(UCenterResultEnum.RECHARGE_ORDER_AMOUNT_ERROR.getValue(), UCenterResultEnum.RECHARGE_ORDER_AMOUNT_ERROR.getComment());
            }
            boolean rechargeSucceed = false;
            //充值成功
            if (userRecharge.getRechargeStatus().equals(RechargeStatusEnum.TRADE_SUCCESS.getValue())) {
                rechargeSucceed = true;
            } else if (userRecharge.getRechargeStatus().equals(RechargeStatusEnum.WAITING_PAY.getValue())) {
                //支付处理中
                //自定义重试5次
                int count = 0;
                while (true) {
                    count++;

                    userRechargeResultDto = userTransactionService.getUserRecharge(rechargeRecordGetRequestDto);
                    if (userRechargeResultDto.isFailed() || userRechargeResultDto.getData() == null) {
                        return resultDto.makeResult(UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getValue(), UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getComment()
                        );
                    }
                    userRecharge = userRechargeResultDto.getData();
                    if (userRecharge == null) {
                        return resultDto.makeResult(UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getValue(),
                                UCenterResultEnum.RECHARGE_ORDER_NOT_FOUND.getComment()
                        );
                    }
                    if (userRecharge.getRechargeAmount().compareTo(requestDto.getChargeAmount()) != 0) {
                        return resultDto.makeResult(UCenterResultEnum.RECHARGE_ORDER_AMOUNT_ERROR.getValue(),
                                UCenterResultEnum.RECHARGE_ORDER_AMOUNT_ERROR.getComment()
                        );
                    }
                    if (userRecharge.getRechargeStatus().equals(RechargeStatusEnum.TRADE_SUCCESS.getValue())) {
                        rechargeSucceed = true;
                        break;
                    } else if (userRecharge.getRechargeStatus().equals(RechargeStatusEnum.TRADE_CLOSED.getValue())) {
                        rechargeSucceed = false;
                        break;
                    }
                    if (count >= 5) {
                        resultDto.failed("支付处理中,请稍后再试");
                        break;
                    }
                    ThreadUtils.sleep((2 * count - 1) & TimeUtils.MILLIS_OF_MINUTE);
                }
            }
            if (rechargeSucceed) {
                RechargeConfirmResultDto rechargeConfirmResultDto = new RechargeConfirmResultDto();
                rechargeConfirmResultDto.setOrderNo(userRecharge.getOrderNo());
                rechargeConfirmResultDto.setSucceedTime(userRecharge.getSucceedTime());
                rechargeConfirmResultDto.setTotalAmount(userRecharge.getRechargeRealAmount());
                rechargeConfirmResultDto.setSucceed(Boolean.TRUE);
                resultDto.setData(rechargeConfirmResultDto);
            } else {
                RechargeConfirmResultDto rechargeConfirmResultDto = new RechargeConfirmResultDto();
                rechargeConfirmResultDto.setOrderNo(userRecharge.getOrderNo());
                rechargeConfirmResultDto.setSucceed(Boolean.FALSE);
                resultDto.setData(rechargeConfirmResultDto);
            }

        } catch (Exception e) {
            log.error("充值确认失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto.success();
    }

    /**
     * 预约订单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<AppointOrderPaymentResultDto> appointPayment(AppointOrderPayRequestDto requestDto) {

        ObjectResultDto<AppointOrderPaymentResultDto> resultDto = new ObjectResultDto<>();
        PayWayEnum payWay = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWay == null) {
            return resultDto.makeResult(UCenterResultEnum.PAY_WAY_EMPTY.getValue(), UCenterResultEnum.PAY_WAY_EMPTY.getComment());
        }
        if (requestDto.getPaymentAmount() == null || requestDto.getPaymentAmount().compareTo(BigDecimal.ZERO) <= 0) {
            return resultDto.makeResult(UCenterResultEnum.AMOUNT_ERR.getValue(), UCenterResultEnum.AMOUNT_ERR.getComment());
        }
        try {

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPaymentAppointOrderLockKey(requestDto.getOrderNo()));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {

                    //获取预约订单
                    ParkingAppointOrderGetRequestDto appointOrderGetRequestDto = new ParkingAppointOrderGetRequestDto();
                    appointOrderGetRequestDto.setCustomerUserId(requestDto.getSessionIdentity().getUserId());
                    appointOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
                    ObjectResultDto<ParkingAppointmentOrderResultDto> appointOrderResultDto = platformAppointOrderService.getAppointOrderApp(appointOrderGetRequestDto);
                    if (appointOrderResultDto.isFailed() || appointOrderResultDto.getData() == null) {
                        return resultDto.makeResult(UCenterResultEnum.APPOINT_ORDER_NOT_FOUND.getValue(),
                                UCenterResultEnum.APPOINT_ORDER_NOT_FOUND.getComment()
                        );
                    }
                    ParkingAppointmentOrderResultDto appointOrder = appointOrderResultDto.getData();
                    PayStatusEnum payStatusEnum = PayStatusEnum.parse(appointOrder.getPayStatus());

                    if (payStatusEnum == null
                            || !(payStatusEnum.getValue().equals(PayStatusEnum.CREATED.getValue()) || payStatusEnum.getValue().equals(PayStatusEnum.PAY_WAITING.getValue()))) {
                        return resultDto.makeResult(UCenterResultEnum.ORDER_PAY_STATUS_ERROR.getValue(),
                                UCenterResultEnum.ORDER_PAY_STATUS_ERROR.getComment()
                        );
                    }
                    //TODO 支付金额处理  2018-11-8 yuhuanlong
                    if ((appointOrder.getPayAmount().compareTo(NumberUtils.amountYuan2FenInt(requestDto.getPaymentAmount())) != 0)) {
                        return resultDto.makeResult(PayResultEnum.ORDER_AMOUNT_ERROR.getValue(), PayResultEnum.ORDER_AMOUNT_ERROR.getComment());
                    }

                    AppointOrderPaymentResultDto paymentResultDto = new AppointOrderPaymentResultDto();
                    Long userId = requestDto.getSessionIdentity().getUserId();
                    Date now = new Date();
                    if (payWay.getValue().equals(PayWayEnum.PACKET.getValue())) {
                        if (StringUtils.isNotEmpty(requestDto.getTradePassword())) {
                            UserTradePasswordCheckRequestDto userCheckTradePasswordRequestDto = new UserTradePasswordCheckRequestDto();
                            userCheckTradePasswordRequestDto.setTradePassword(requestDto.getTradePassword());
                            userCheckTradePasswordRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                            ResultDto checkResult = userService.checkTradePassword(userCheckTradePasswordRequestDto);
                            if (!checkResult.getCode().equals(UCenterResultEnum.USER_TRADE_PASSWORD_SUCCESS.getValue())) {
                                return resultDto.makeResult(UCenterResultEnum.TRADE_PASSWORD_ERR.getValue(),
                                        UCenterResultEnum.TRADE_PASSWORD_ERR.getComment());
                            }
                        }

                        AccountBalanceAvailableCheckRequestDto parkingBalanceJudgeRequestDto = new AccountBalanceAvailableCheckRequestDto();
                        parkingBalanceJudgeRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                        parkingBalanceJudgeRequestDto.setPaymentAmount(requestDto.getPaymentAmount());

                        ObjectResultDto<AccountBalanceAvailableCheckResultDto> objectResultDto = accountTransactionService.checkAccountAvailable(parkingBalanceJudgeRequestDto);
                        if (objectResultDto.isFailed() || objectResultDto.getData() == null
                                || !objectResultDto.getData().getJudgeBalanced()) {

                            return resultDto.makeResult(UCenterResultEnum.PAYMENT_AMOUNT_NOT_ENOUGH.getValue(), UCenterResultEnum.PAYMENT_AMOUNT_NOT_ENOUGH.getComment());
                        }

                        log.debug("------debitToAccountTcc[订单{}余额支付减款开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                        AccountBalanceSubtractRequestDto accountBalanceSubtractRequestDto = new AccountBalanceSubtractRequestDto();
                        accountBalanceSubtractRequestDto.setUserId(userId);
                        accountBalanceSubtractRequestDto.setAmount(requestDto.getPaymentAmount());
                        accountBalanceSubtractRequestDto.setBizNo(requestDto.getOrderNo());
                        accountBalanceSubtractRequestDto.setBizType(BizTypeEnum.PAYMENT.getValue());
                        accountBalanceSubtractRequestDto.setTransactionNo("");
                        accountBalanceSubtractRequestDto.setRemark("余额支付");
                        accountTransactionService.debitToAccountTcc(null, accountBalanceSubtractRequestDto);

                        log.debug("------completeSuccessPaymentOrder[订单{}余额支付减款开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                        log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付修改预约记录开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                        CompletePaymentAppointmentOrderRequestDto completePaymentParkingOrderRequestDto = new CompletePaymentAppointmentOrderRequestDto();
                        completePaymentParkingOrderRequestDto.setOrderNo(requestDto.getOrderNo());
                        completePaymentParkingOrderRequestDto.setActualAmount(NumberUtils.amountYuan2Fen(requestDto.getPaymentAmount()).intValue());
                        completePaymentParkingOrderRequestDto.setSucceedPayTime(now);
                        completePaymentParkingOrderRequestDto.setPayWay(payWay.getValue());
                        completePaymentParkingOrderRequestDto.setPayOrderNo("");
                        completePaymentParkingOrderRequestDto.setPayType(PayTypeEnum.PACKET_BALANCE.getValue());
                        parkingOrderTransactionService.completeSuccessPaymentAppointmentOrder(null, completePaymentParkingOrderRequestDto);

                        log.debug("------completeSuccessPaymentAppointmentOrder[订单{}完成支付修改预约记录开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                    } else {

                        //订单支付下单
                        PlacePaymentOrderRequestDto placePaymentOrderRequestDto = new PlacePaymentOrderRequestDto();
                        placePaymentOrderRequestDto.setPayerUserId(userId);
                        placePaymentOrderRequestDto.setBizOrderType(BizTypeEnum.APPOINTMENT.getValue());
                        placePaymentOrderRequestDto.setBizOrderNo(appointOrder.getOrderNo());
                        if (payWay.getValue().equals(PayWayEnum.ALIPAY.getValue())) {
                            placePaymentOrderRequestDto.setPayType(PayTypeEnum.ALI_APP.getValue());
                        } else if (payWay.getValue().equals(PayWayEnum.WEIXINPAY.getValue())) {
                            placePaymentOrderRequestDto.setPayType(PayTypeEnum.WX_APP.getValue());
                        } else {
                            return resultDto.makeResult(UCenterResultEnum.PAY_WAY_NOT_SUPPORT.getValue(),
                                    UCenterResultEnum.PAY_WAY_NOT_SUPPORT.getComment()
                            );
                        }
                        placePaymentOrderRequestDto.setPayWay(requestDto.getPayWay());
                        placePaymentOrderRequestDto.setProductName("预定订单支付");
                        placePaymentOrderRequestDto.setOrderDate(now);
                        placePaymentOrderRequestDto.setOrderTime(now);
                        Integer orderAmount = NumberUtils.amountYuan2Fen(requestDto.getPaymentAmount()).intValue();
                        placePaymentOrderRequestDto.setOrderAmount(orderAmount);
                        placePaymentOrderRequestDto.setRemark("预定订单支付");
                        placePaymentOrderRequestDto.setOrderIp(requestDto.getSpbillCreateIp());
                        placePaymentOrderRequestDto.setOrderPeriod(15);
                        ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrderResultDto = tradePaymentManagerService.placePaymentOrder(placePaymentOrderRequestDto);
                        if (placePaymentOrderResultDto.isFailed() || placePaymentOrderResultDto.getData() == null) {
                            return resultDto.makeResult(UCenterResultEnum.APPOINT_ORDER_PAY_FAILED.getValue(),
                                    UCenterResultEnum.APPOINT_ORDER_PAY_FAILED.getComment()
                            );
                        }

                        PlacePaymentOrderResultDto placePaymentOrder = placePaymentOrderResultDto.getData();
                        //用户支付下单
                        ParkingAppointmentOrderPaymentRequestDto userAppointmentOrderPaymentRequestDto = new ParkingAppointmentOrderPaymentRequestDto();
                        userAppointmentOrderPaymentRequestDto.setOrderUuid(StringUtils.getUUID());
                        userAppointmentOrderPaymentRequestDto.setPaymentAmount(requestDto.getPaymentAmount());
                        userAppointmentOrderPaymentRequestDto.setPayWay(requestDto.getPayWay());
                        userAppointmentOrderPaymentRequestDto.setOrderNo(requestDto.getOrderNo());
                        userAppointmentOrderPaymentRequestDto.setPayOrderNo(placePaymentOrder.getPayOrderNo());
                        userAppointmentOrderPaymentRequestDto.setSessionIdentity(requestDto.getSessionIdentity());

                        ResultDto placeOrderResult = accountTransactionService.placeAppointmentOrderPayment(userAppointmentOrderPaymentRequestDto);
                        if (placeOrderResult.isFailed()) {
                            return resultDto.makeResult(UCenterResultEnum.APPOINT_ORDER_PAY_FAILED.getValue(),
                                    UCenterResultEnum.APPOINT_ORDER_PAY_FAILED.getComment()
                            );
                        }
                        //TODO 更新预约订单支付中
                        paymentResultDto.setOrderNo(appointOrder.getOrderNo());
                        paymentResultDto.setAlipayOrderInfo(placePaymentOrder.getAlipayOrderInfo());
                        paymentResultDto.setAppId(placePaymentOrder.getAppId());
                        paymentResultDto.setNonceStr(placePaymentOrder.getNonceStr());
                        paymentResultDto.setPackages(placePaymentOrder.getPackages());
                        paymentResultDto.setPartnerId(placePaymentOrder.getPartnerId());
                        paymentResultDto.setPrepayId(placePaymentOrder.getPrepayId());
                        paymentResultDto.setPaySign(placePaymentOrder.getPaySign());
                        paymentResultDto.setSignType(placePaymentOrder.getSignType());
                        paymentResultDto.setTimeStamp(placePaymentOrder.getTimeStamp());
                        paymentResultDto.setTradeType(placePaymentOrder.getTradeType());
                    }
                    resultDto.setData(paymentResultDto);
                    resultDto.success();
                }
            } catch (Exception e) {
                log.error("获取分布式锁时抛错：", e);
                resultDto.failed();
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
        } catch (Exception e) {
            log.error("预定订单支付失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取预约订单支付分布式锁键
     *
     * @param orderNo orderNo
     * @return
     */
    private String getPaymentAppointOrderLockKey(String orderNo) {
        return "lock:parking.pay.appoint.order_" + orderNo;
    }
}
