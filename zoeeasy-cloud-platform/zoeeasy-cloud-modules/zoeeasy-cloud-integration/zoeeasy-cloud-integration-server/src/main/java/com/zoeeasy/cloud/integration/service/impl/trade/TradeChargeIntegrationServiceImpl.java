package com.zoeeasy.cloud.integration.service.impl.trade;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.core.enums.BizTypeEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayTypeEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.integration.pay.TradePaymentIntegrationService;
import com.zoeeasy.cloud.integration.trade.TradeChargeIntegrationService;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeRequestDto;
import com.zoeeasy.cloud.integration.trade.dto.ParkingOrderChargeResultDto;
import com.zoeeasy.cloud.integration.trade.validator.ParkingOrderChargeValidator;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetPlateOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付收款业务处理
 *
 * @author walkman
 */
@Service("tradeChargeIntegrationService")
@Slf4j
public class TradeChargeIntegrationServiceImpl implements TradeChargeIntegrationService {

    @Autowired
    private TradePaymentIntegrationService tradePaymentIntegrationService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    /**
     * 订单二维码线下收款
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<ParkingOrderChargeResultDto> chargeParkingOrder(@FluentValid({ParkingOrderChargeValidator.class}) ParkingOrderChargeRequestDto requestDto) {

        ObjectResultDto<ParkingOrderChargeResultDto> resultDto = new ObjectResultDto<>();

        try {
            //获取停车账单
            ParkingOrderGetPlateOrderNoRequestDto parkingOrderGetRequestDto = new ParkingOrderGetPlateOrderNoRequestDto();
            parkingOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            parkingOrderGetRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByPlateOrderNo(parkingOrderGetRequestDto);
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
                        if (PayStatusEnum.PAY_SUCCESS.getValue().equals(payStatusEnum.getValue())) {
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
                    //支付方式校验
                    PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());
                    PayTypeEnum payTypeEnum = PayTypeEnum.valuedOf(requestDto.getPayType());
                    if (payWayEnum == null || payTypeEnum == null) {
                        return resultDto.makeResult(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
                    }
                    //是否需要支付
                    boolean needPay = true;
                    if (parkingOrder.getSettle()) {
                        //如果已结算,校验结算金额
                        if ((NumberUtils.amountFen2Yuan(BigDecimal.valueOf(parkingOrder.getPayableAmount())).compareTo(requestDto.getPaymentAmount()) != 0)) {
                            return resultDto.makeResult(PayResultEnum.ORDER_AMOUNT_ERROR.getValue(), PayResultEnum.ORDER_AMOUNT_ERROR.getComment());
                        }
                    }
                    //实际收款用户ID
                    Long userId = FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId();
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
                    ParkingOrderChargeResultDto chargeResultDto = new ParkingOrderChargeResultDto();
                    if (needPay) {
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

                        ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrderResultDto = tradePaymentIntegrationService.placePaymentOrder(placePaymentOrderRequestDto);
                        if (placePaymentOrderResultDto.isFailed() || placePaymentOrderResultDto.getData() == null) {
                            return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAY_FAILED.getValue(), PayResultEnum.PARKING_ORDER_PAY_FAILED.getComment()
                            );
                        }
                        PlacePaymentOrderResultDto placePaymentOrder = placePaymentOrderResultDto.getData();

                        //TODO 用户收款订单下单

                        //封装支付下单结果
                        chargeResultDto.setNeedPay(needPay);
                        chargeResultDto.setPaymentAmount(NumberUtils.formatAmountYuan(payAmount));
                        chargeResultDto.setOrderNo(placePaymentOrder.getPayOrderNo());
                        chargeResultDto.setTradeType(placePaymentOrder.getTradeType());
                        chargeResultDto.setQrCode(placePaymentOrder.getQrCode());
                        chargeResultDto.setQrCode(placePaymentOrder.getQrCodeUrl());
                        resultDto.setData(chargeResultDto);
                    } else {
                        chargeResultDto.setNeedPay(needPay);
                        chargeResultDto.setPaymentAmount(NumberUtils.formatAmountYuan(payAmount));
                        resultDto.setData(chargeResultDto);
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

}
