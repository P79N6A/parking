package com.zhuyitech.parking.integration.service.impl;


import com.scapegoat.infrastructure.common.utils.*;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.validate.annotaion.ValidateFilters;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentConfirmRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.PaymentCheckRequestDto;
import com.zhuyitech.parking.integration.order.dto.result.ParkingOrderPaymentResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentCheckResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentConfirmResultDto;
import com.zhuyitech.parking.integration.trade.TradeIntegrationService;
import com.zhuyitech.parking.integration.validatefilter.ParkingOrderPaymentValidateFilter;
import com.zhuyitech.parking.pms.enums.PmsResultEnum;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceAvailableCheckRequestDto;
import com.zhuyitech.parking.ucc.dto.request.account.AccountBalanceSubtractRequestDto;
import com.zhuyitech.parking.ucc.dto.request.trade.UserParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.ucc.dto.result.AccountBalanceAvailableCheckResultDto;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zhuyitech.parking.ucc.service.api.UserService;
import com.zhuyitech.parking.ucc.user.dto.UserTradePasswordCheckRequestDto;
import com.zhuyitech.parking.ucc.user.dto.UserTradePasswordCheckResultDto;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetByOrderNoRequestDto;
import com.zoeeasy.cloud.order.transaction.ParkingOrderTransactionService;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.transaction.dto.request.CompletePaymentParkingOrderRequestDto;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pay.trade.TradePaymentManagerService;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 交易集成服务
 *
 * @author walkman
 */
@Service("tradeIntegrationService")
@Slf4j
public class TradeIntegrationServiceImpl implements TradeIntegrationService {

    @Autowired
    private ParkingOrderTransactionService parkingOrderTransactionService;

    @Autowired
    private TradePaymentManagerService tradePaymentManagerService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private AccountTransactionService accountTransactionService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private UserService userService;

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    /**
     * 停车账单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @ValidateFilters(filterClasses = {ParkingOrderPaymentValidateFilter.class})
    public ObjectResultDto<ParkingOrderPaymentResultDto> payParkingOrder(ParkingOrderPaymentRequestDto requestDto) {
        ObjectResultDto<ParkingOrderPaymentResultDto> resultDto = new ObjectResultDto<>();
        //支付方式校验
        PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());
        if (payWayEnum == null) {
            return resultDto.makeResult(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
        }
        PayTypeEnum payTypeEnum = PayTypeEnum.parse(requestDto.getPayType());
        if (PayWayEnum.WEIXINPAY.getValue().equals(requestDto.getPayWay()) &&
                (StringUtils.isEmpty(requestDto.getPayType()) || null == payTypeEnum)) {
            payTypeEnum = PayTypeEnum.WX_APP;
        }
        if (PayWayEnum.ALIPAY.getValue().equals(payWayEnum.getValue())) {
            payTypeEnum = PayTypeEnum.ALI_APP;
        } else if (PayWayEnum.PACKET.getValue().equals(payWayEnum.getValue())) {
            payTypeEnum = PayTypeEnum.PACKET_BALANCE;
        }
        if (payTypeEnum == null) {
            return resultDto.makeResult(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
        }
        if (PayTypeEnum.WX_JSAPI.getValue().equals(payTypeEnum.getValue()) && StringUtils.isEmpty(requestDto.getOpenId())) {
            return resultDto.makeResult(PayResultEnum.OPENID_EMPTY.getValue(), PayResultEnum.OPENID_EMPTY.getComment());
        }

        try {

            //获取停车账单
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_ORDER_NOT_FOUND.getValue(), PmsResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            }
            ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPaymentParkingOrderLockKey(parkingOrder.getOrderNo()));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME * 3L);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //支付状态判断
                    PayStatusEnum payStatusEnum = PayStatusEnum.parse(parkingOrder.getPayStatus());
                    if (payStatusEnum == null) {
                        return resultDto.makeResult(PayResultEnum.ORDER_PAY_STATUS_ERROR.getValue(),
                                PayResultEnum.ORDER_PAY_STATUS_ERROR.getComment()
                        );
                    } else {
                        //支付成功，无需支付
                        if (payStatusEnum.getValue().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                            return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAYED_CANT_REPEAT.getValue(),
                                    PayResultEnum.PARKING_ORDER_PAYED_CANT_REPEAT.getComment()
                            );
                        } else if (!(payStatusEnum.getValue().equals(PayStatusEnum.CREATED.getValue())
                                || payStatusEnum.getValue().equals(PayStatusEnum.PAY_WAITING.getValue()))) {
                            return resultDto.makeResult(PayResultEnum.ORDER_PAY_STATUS_ERROR.getValue(),
                                    PayResultEnum.ORDER_PAY_STATUS_ERROR.getComment()
                            );
                        }
                    }
                    //是否允许支付
                    if (!parkingOrder.getPayable()) {
                        return resultDto.makeResult(PayResultEnum.ORDER_PAY_STATUS_ERROR.getValue(),
                                PayResultEnum.ORDER_PAY_STATUS_ERROR.getComment()
                        );
                    }
                    if (PayWayEnum.PACKET.getValue().equals(payWayEnum.getValue())) {
                        //如果钱包支付，校验钱包密码
                        if (StringUtils.isNotEmpty(requestDto.getTradePassword())) {
                            UserTradePasswordCheckRequestDto userCheckTradePasswordRequestDto = new UserTradePasswordCheckRequestDto();
                            userCheckTradePasswordRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                            userCheckTradePasswordRequestDto.setTradePassword(requestDto.getTradePassword());
                            ObjectResultDto<UserTradePasswordCheckResultDto> checkResult = userService.checkTradePassword(userCheckTradePasswordRequestDto);
                            if (checkResult.getData() == null || !checkResult.getData().getPassed()) {
                                return resultDto.makeResult(PayResultEnum.TRADE_PASSWORD_ERR.getValue(),
                                        PayResultEnum.TRADE_PASSWORD_ERR.getComment());
                            }
                        } else {
                            return resultDto.makeResult(PayResultEnum.TRADE_PASSWORD_EMPTY.getValue(),
                                    PayResultEnum.TRADE_PASSWORD_EMPTY.getComment());
                        }
                    }
                    //是否需要支付
                    Boolean needPay = true;
                    if (parkingOrder.getSettle()) {
                        //如果已结算,校验结算金额
                        if ((NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingOrder.getPayableAmount())).compareTo(requestDto.getPaymentAmount()) != 0)) {
                            return resultDto.makeResult(PayResultEnum.ORDER_AMOUNT_ERROR.getValue(),
                                    PayResultEnum.ORDER_AMOUNT_ERROR.getComment()
                            );
                        }
                    }
                    //实际支付用户ID
                    Long userId = requestDto.getSessionIdentity().getUserId();
                    Date now = DateUtils.now();
                    //平台订单准备支付
                    ParkingOrderPreparePaymentRequestDto parkingOrderPreparePaymentRequestDto = new ParkingOrderPreparePaymentRequestDto();
                    parkingOrderPreparePaymentRequestDto.setOrderNo(parkingOrder.getOrderNo());
                    parkingOrderPreparePaymentRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                    parkingOrderPreparePaymentRequestDto.setRecordNo(parkingOrder.getRecordNo());
                    parkingOrderPreparePaymentRequestDto.setPayedUserId(userId);
                    parkingOrderPreparePaymentRequestDto.setPayTime(now);
                    parkingOrderPreparePaymentRequestDto.setPayWay(payWayEnum.getValue());
                    parkingOrderPreparePaymentRequestDto.setPayType(payTypeEnum.getValue());
                    parkingOrderPreparePaymentRequestDto.setPayAmount(NumberUtils.amountYuan2FenInt(requestDto.getPaymentAmount()));
                    ObjectResultDto<ParkingOrderPreparePaymentResultDto> paymentResultDtoObject = parkingOrderIntegrationService.preparePayParkingOrder(parkingOrderPreparePaymentRequestDto);
                    if (paymentResultDtoObject.isFailed() || paymentResultDtoObject.getData() == null) {
                        return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAY_FAILED.getValue(),
                                PayResultEnum.PARKING_ORDER_PAY_FAILED.getComment());
                    }
                    ParkingOrderPreparePaymentResultDto preparePaymentResultDto = paymentResultDtoObject.getData();
                    needPay = preparePaymentResultDto.getNeedPay();
                    //支付金额
                    BigDecimal payAmount = NumberUtils.amountFen2Yuan(preparePaymentResultDto.getPayableAmount());
                    if (PayWayEnum.PACKET.getValue().equals(payWayEnum.getValue())) {
                        //钱包余额支付
                        AccountBalanceAvailableCheckRequestDto balanceAvailableCheckRequestDto = new AccountBalanceAvailableCheckRequestDto();
                        balanceAvailableCheckRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                        balanceAvailableCheckRequestDto.setPaymentAmount(payAmount);
                        ObjectResultDto<AccountBalanceAvailableCheckResultDto> objectResultDto = accountTransactionService.checkAccountAvailable(balanceAvailableCheckRequestDto);
                        if (objectResultDto.isFailed() || objectResultDto.getData() == null || !objectResultDto.getData().getJudgeBalanced()) {
                            return resultDto.makeResult(PayResultEnum.PAYMENT_AMOUNT_NOT_ENOUGH.getValue(),
                                    PayResultEnum.PAYMENT_AMOUNT_NOT_ENOUGH.getComment());
                        }
                    }

                    ParkingOrderPaymentResultDto paymentResultDto = new ParkingOrderPaymentResultDto();
                    if (needPay) {

                        if (PayWayEnum.PACKET.getValue().equals(payWayEnum.getValue())) {
                            //钱包支付

                            log.debug("------debitToAccountTcc[订单{}余额支付减款开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                            AccountBalanceSubtractRequestDto accountBalanceSubtractRequestDto = new AccountBalanceSubtractRequestDto();
                            accountBalanceSubtractRequestDto.setUserId(userId);
                            accountBalanceSubtractRequestDto.setAmount(payAmount);
                            accountBalanceSubtractRequestDto.setBizType(BizTypeEnum.PAYMENT.getValue());
                            accountBalanceSubtractRequestDto.setBizNo(requestDto.getOrderNo());
                            accountBalanceSubtractRequestDto.setTransactionNo("");
                            accountBalanceSubtractRequestDto.setRemark("余额支付");
                            accountTransactionService.debitToAccountTcc(null, accountBalanceSubtractRequestDto);

                            log.debug("------completeSuccessPaymentOrder[订单{}余额支付减款开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                            log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付修改停车记录开始时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                            CompletePaymentParkingOrderRequestDto completePaymentParkingOrderRequestDto = new CompletePaymentParkingOrderRequestDto();
                            completePaymentParkingOrderRequestDto.setPayedUserId(userId);
                            completePaymentParkingOrderRequestDto.setOrderNo(parkingOrder.getOrderNo());
                            completePaymentParkingOrderRequestDto.setActualAmount(NumberUtils.amountYuan2FenInt(payAmount));
                            completePaymentParkingOrderRequestDto.setSucceedPayTime(now);
                            completePaymentParkingOrderRequestDto.setPayOrderNo("");
                            completePaymentParkingOrderRequestDto.setPayWay(PayWayEnum.PACKET.getValue());
                            completePaymentParkingOrderRequestDto.setPayType(PayTypeEnum.PACKET_BALANCE.getValue());
                            parkingOrderTransactionService.completeSuccessPaymentParkingOrder(null, completePaymentParkingOrderRequestDto);

                            log.debug("------completeSuccessPaymentParkingOrder[订单{}完成支付修改停车记录结束时间{}]------", requestDto.getOrderNo(), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));

                            if (payAmount.compareTo(BigDecimal.ZERO) > 0) {
                                paymentResultDto.setNeedPay(Boolean.TRUE);
                            } else {
                                paymentResultDto.setNeedPay(Boolean.FALSE);
                            }
                            paymentResultDto.setPaymentAmount(NumberUtils.formatAmountYuan(payAmount));
                            resultDto.setData(paymentResultDto);
                        } else {

                            //订单支付下单
                            PlacePaymentOrderRequestDto placePaymentOrderRequestDto = new PlacePaymentOrderRequestDto();
                            placePaymentOrderRequestDto.setTenantId(parkingOrder.getTenantId());
                            placePaymentOrderRequestDto.setParkingId(parkingOrder.getParkingId());
                            placePaymentOrderRequestDto.setPayerUserId(userId);
                            placePaymentOrderRequestDto.setBizOrderType(BizTypeEnum.PAYMENT.getValue());
                            placePaymentOrderRequestDto.setBizOrderNo(parkingOrder.getOrderNo());
                            //支付方式
                            placePaymentOrderRequestDto.setPayWay(payWayEnum.getValue());
                            //支付类型
                            placePaymentOrderRequestDto.setPayType(payTypeEnum.getValue());
                            placePaymentOrderRequestDto.setProductName("停车账单缴费");
                            placePaymentOrderRequestDto.setOrderDate(now);
                            placePaymentOrderRequestDto.setOrderTime(now);
                            placePaymentOrderRequestDto.setOrderAmount(NumberUtils.amountYuan2FenInt(payAmount));
                            placePaymentOrderRequestDto.setRemark("停车账单缴费");
                            placePaymentOrderRequestDto.setOrderIp(requestDto.getSpbillCreateIp());
                            placePaymentOrderRequestDto.setOrderPeriod(15);
                            placePaymentOrderRequestDto.setOpenId(requestDto.getOpenId());
                            ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrderResultDto = tradePaymentManagerService.placePaymentOrder(placePaymentOrderRequestDto);
                            if (placePaymentOrderResultDto.isFailed() || placePaymentOrderResultDto.getData() == null) {
                                return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAY_FAILED.getValue(),
                                        PayResultEnum.PARKING_ORDER_PAY_FAILED.getComment()
                                );
                            }
                            PlacePaymentOrderResultDto placePaymentOrder = placePaymentOrderResultDto.getData();

                            //用户支付下单
                            UserParkingOrderPaymentRequestDto userParkingOrderPaymentRequestDto = new UserParkingOrderPaymentRequestDto();
                            userParkingOrderPaymentRequestDto.setOrderUuid(StringUtils.getUUID());
                            userParkingOrderPaymentRequestDto.setPaymentAmount(payAmount);
                            userParkingOrderPaymentRequestDto.setPayWay(requestDto.getPayWay());
                            userParkingOrderPaymentRequestDto.setOrderNo(requestDto.getOrderNo());
                            userParkingOrderPaymentRequestDto.setPayOrderNo(placePaymentOrder.getPayOrderNo());
                            userParkingOrderPaymentRequestDto.setSessionIdentity(requestDto.getSessionIdentity());
                            ResultDto placeOrderResult = accountTransactionService.placeParkingOrderPayment(userParkingOrderPaymentRequestDto);
                            if (placeOrderResult.isFailed()) {
                                return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAY_FAILED.getValue(),
                                        PayResultEnum.PARKING_ORDER_PAY_FAILED.getComment()
                                );
                            }

                            //封装支付下单结果
                            paymentResultDto.setNeedPay(needPay);
                            paymentResultDto.setPaymentAmount(NumberUtils.formatAmountYuan(payAmount));
                            paymentResultDto.setAlipayOrderInfo(placePaymentOrder.getAlipayOrderInfo());
                            paymentResultDto.setAppId(placePaymentOrder.getAppId());
                            paymentResultDto.setNonceStr(placePaymentOrder.getNonceStr());
                            paymentResultDto.setPackages(placePaymentOrder.getPackages());
                            paymentResultDto.setPartnerId(placePaymentOrder.getPartnerId());
                            paymentResultDto.setPrepayId(placePaymentOrder.getPrepayId());
                            paymentResultDto.setPaySign(placePaymentOrder.getPaySign());
                            paymentResultDto.setSignType(placePaymentOrder.getSignType());
                            paymentResultDto.setOrderNo(parkingOrder.getOrderNo());
                            paymentResultDto.setTimeStamp(placePaymentOrder.getTimeStamp());
                            paymentResultDto.setTradeType(placePaymentOrder.getTradeType());
                        }
                        resultDto.setData(paymentResultDto);
                    } else {
                        paymentResultDto.setNeedPay(needPay);
                        paymentResultDto.setPaymentAmount(NumberUtils.formatAmountYuan(payAmount));
                        resultDto.setData(paymentResultDto);
                    }
                    resultDto.success();
                } else {
                    log.error("获取分布式锁时抛错：");
                }
            } catch (Exception e) {
                log.error("停车订单支付异常：{}", e.getMessage());
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
        } catch (Exception e) {
            log.error("订单支付失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取停车订单支付分布式锁键
     *
     * @param orderNo orderNo
     * @return
     */

    private String getPaymentParkingOrderLockKey(String orderNo) {
        return "lock:parking.pay.parking.order_" + orderNo;
    }

    /**
     * 停车账单支付确认
     *
     * @param requestDto 请求参数
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PaymentConfirmResultDto> confirmParkingOrderPayment(ParkingOrderPaymentConfirmRequestDto
                                                                                       requestDto) {
        ObjectResultDto<PaymentConfirmResultDto> resultDto = new ObjectResultDto<>();
        //获取停车记录
        ParkingOrderGetByOrderNoRequestDto parkingOrderGetRequestDto = new ParkingOrderGetByOrderNoRequestDto();
        parkingOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
        ObjectResultDto<ParkingOrderResultDto> parkingOrderObjectResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetRequestDto);
        if (parkingOrderObjectResultDto.isFailed() || parkingOrderObjectResultDto.getData() == null) {
            return resultDto.makeResult(PayResultEnum.PARKING_RECORD_EMPTY.getValue(),
                    PayResultEnum.PARKING_RECORD_EMPTY.getComment()
            );
        }
        ParkingOrderResultDto parkingOrderResultDto = parkingOrderObjectResultDto.getData();
        if (NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingOrderResultDto.getPayableAmount())).compareTo(requestDto.getPayAmount()) != 0) {
            return resultDto.makeResult(PayResultEnum.ORDER_AMOUNT_ERROR.getValue(),
                    PayResultEnum.ORDER_AMOUNT_ERROR.getComment()
            );
        }
        boolean paySuccess = false;
        //付款成功
        if (parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            paySuccess = true;
        } else if (parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.WAITING_PAYMENT_RESULT.getValue())) {
            //支付处理中
            //自定义重试5次
            int count = 0;
            while (true) {
                count++;

                parkingOrderObjectResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetRequestDto);
                if (parkingOrderObjectResultDto.isFailed() || parkingOrderObjectResultDto.getData() == null) {
                    return resultDto.makeResult(PayResultEnum.PARKING_RECORD_EMPTY.getValue(),
                            PayResultEnum.PARKING_RECORD_EMPTY.getComment()
                    );
                }
                parkingOrderResultDto = parkingOrderObjectResultDto.getData();
                if (parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    paySuccess = true;
                    break;
                } else if (parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.PAY_FAILED.getValue())) {
                    break;
                }
                if (count >= 5) {
                    resultDto.failed("支付处理中,请稍后再试");
                    break;
                }
                ThreadUtils.sleep((2 * count - 1) & TimeUtils.MILLIS_OF_MINUTE);
            }
        }
        if (paySuccess) {
            PaymentConfirmResultDto paymentConfirmResultDto = new PaymentConfirmResultDto();
            paymentConfirmResultDto.setOrderNo(parkingOrderResultDto.getOrderNo());
            paymentConfirmResultDto.setSucceedTime(parkingOrderResultDto.getPayTime());
            paymentConfirmResultDto.setTotalAmount(NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingOrderResultDto.getActualPayAmount())));
            paymentConfirmResultDto.setSucceed(Boolean.TRUE);
            resultDto.setData(paymentConfirmResultDto);
        } else {
            PaymentConfirmResultDto rechargeConfirmResultDto = new PaymentConfirmResultDto();
            rechargeConfirmResultDto.setOrderNo(parkingOrderResultDto.getOrderNo());
            rechargeConfirmResultDto.setSucceed(Boolean.FALSE);
            resultDto.setData(rechargeConfirmResultDto);
        }
        return resultDto.success();
    }


    /**
     * 停车账单支付判断
     *
     * @param requestDto 请求参数
     * @return
     */

    @Override
    public ObjectResultDto<PaymentCheckResultDto> payCheck(PaymentCheckRequestDto requestDto) {
        ObjectResultDto<PaymentCheckResultDto> objectResultDto = new ObjectResultDto<>();
        PaymentCheckResultDto paymentCheckResultDto = new PaymentCheckResultDto();
        paymentCheckResultDto.setPayCheck(Boolean.FALSE);
        //获取停车记录
        try {
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderObjectResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetRequestDto);
            if (parkingOrderObjectResultDto.isFailed() || parkingOrderObjectResultDto.getData() == null) {
                return objectResultDto.makeResult(PayResultEnum.PARKING_RECORD_EMPTY.getValue(),
                        PayResultEnum.PARKING_RECORD_EMPTY.getComment()
                );
            }
            ParkingOrderResultDto parkingOrderResultDto = parkingOrderObjectResultDto.getData();
            if (!(parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.CREATED.getValue())
                    || parkingOrderResultDto.getPayStatus().equals(PayStatusEnum.PAY_WAITING.getValue()))) {
                return objectResultDto.makeResult(PayResultEnum.ORDER_PAYED.getValue(),
                        PayResultEnum.ORDER_PAYED.getComment()
                );
            }
            paymentCheckResultDto.setPayCheck(Boolean.TRUE);
            objectResultDto.setData(paymentCheckResultDto);
        } catch (Exception e) {
            log.error("停车账单支付判断失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto.success();
    }
}

