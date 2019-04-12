package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.core.session.SessionParkingInfo;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.core.enums.*;
import com.zoeeasy.cloud.integration.inspect.InspectParkOrderIntegrationService;
import com.zoeeasy.cloud.integration.inspect.InspectPreparePayParkingOrderService;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.inspect.InspectParkingOrderService;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectGetParkingOrderListRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectParkingOrderListPageRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.result.InspectParkingOrderResultDto;
import com.zoeeasy.cloud.order.inspect.validator.InspectUpdateParkingOrderRequestDtoValidator;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.pay.enums.PayResultEnum;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/11/9 0009
 */
@Service("inspectParkOrderIntegrationService")
@Slf4j
public class InspectParkOrderIntegrationServiceImpl implements InspectParkOrderIntegrationService {

    @Autowired
    private InspectParkingOrderService inspectParkingOrderService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private InspectPreparePayParkingOrderService inspectPreparePayParkingOrderService;

    @Autowired
    private LockFactory lockFactory;

    /**
     * 分页获取用户停车订单
     *
     * @param requestDto ParkingOrderQueryByPlatePageRequestDto
     * @return ParkingOrderViewResultDto
     */
    @Override
    public PagedResultDto<InspectParkingOrderResultDto> getParkingOrderPageList(InspectParkingOrderListPageRequestDto requestDto) {
        PagedResultDto<InspectParkingOrderResultDto> pagedResultDto = new PagedResultDto<>();
        try {
            SessionParkingInfo currentParking = FundamentalRequestContext.getFundamentalRequestContext().getCurrentParking();
            if (currentParking == null) {
                return pagedResultDto.makeResult(PmsResultEnum.PARKING_NOT_EXIST.getValue(),
                        PmsResultEnum.PARKING_NOT_EXIST.getComment());
            }
            InspectGetParkingOrderListRequestDto inspectGetParkingOrderListRequestDto = new InspectGetParkingOrderListRequestDto();
            inspectGetParkingOrderListRequestDto.setParkingId(currentParking.getParkingId());
            inspectGetParkingOrderListRequestDto.setPageNo(requestDto.getPageNo());
            inspectGetParkingOrderListRequestDto.setPageSize(requestDto.getPageSize());
            if (null != requestDto.getPayStatus()) {
                inspectGetParkingOrderListRequestDto.setPayStatus(requestDto.getPayStatus());
            }
            PagedResultDto<ParkingOrderResultDto> parkingOrderPage = inspectParkingOrderService.getParkingOrderPageList(inspectGetParkingOrderListRequestDto);
            if (parkingOrderPage.isSuccess() && null != parkingOrderPage.getItems()) {
                List<ParkingOrderResultDto> records = parkingOrderPage.getItems();
                List<InspectParkingOrderResultDto> parkingResultDtoList = new ArrayList<>();
                for (ParkingOrderResultDto parkingOrder : records) {
                    InspectParkingOrderResultDto parkingOrderViewResultDto = buildParkingOrderView(parkingOrder);
                    parkingResultDtoList.add(parkingOrderViewResultDto);
                }
                pagedResultDto.setPageNo(parkingOrderPage.getPageNo());
                pagedResultDto.setPageSize(parkingOrderPage.getPageSize());
                pagedResultDto.setTotalCount(parkingOrderPage.getTotalCount());
                pagedResultDto.setItems(parkingResultDtoList);
            }
            pagedResultDto.success();
        } catch (Exception e) {
            log.error("获取停车订单失败,异常信息{}", e.getMessage());
            pagedResultDto.failed();
        }
        return pagedResultDto;
    }

    /**
     * 创建账单信息视图对象
     *
     * @param buildDto ParkingOrderViewBuildDto
     * @return ParkingOrderViewResultDto
     */
    private InspectParkingOrderResultDto buildParkingOrderView(ParkingOrderResultDto buildDto) {
        InspectParkingOrderResultDto parkingOrderViewResultDto = new InspectParkingOrderResultDto();
        parkingOrderViewResultDto.setOrderNo(buildDto.getOrderNo());
        parkingOrderViewResultDto.setParkingName(buildDto.getParkingName());
        parkingOrderViewResultDto.setParkingLotNumber(buildDto.getParkingLotNumber());
        //停车开始时间
        if (buildDto.getStartTime() != null) {
            parkingOrderViewResultDto.setParkingTime(
                    DateUtils.formatDate(buildDto.getStartTime(), DateUtils.DATE_FORMAT));
            parkingOrderViewResultDto.setStartTime(
                    DateUtils.formatDate(buildDto.getStartTime(), DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        //停车中
        if (buildDto.getEndTime() == null || buildDto.getEndTime().compareTo(DateTimeUtils.getDateMax()) == 0) {
            Date now = DateUtils.now();
            //停车时长
            parkingOrderViewResultDto.setEndTime("");
            long timeMillis = now.getTime() - buildDto.getStartTime().getTime();
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));
            //如果未结算则试算停车费用
            //停车费用
            BigDecimal payableAmount = BigDecimal.ZERO;
            if (!buildDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                ParkingAmountCalculateRequestDto parkingAmountCalculateRequestDto =
                        new ParkingAmountCalculateRequestDto();
                parkingAmountCalculateRequestDto.setParkingId(buildDto.getParkingId());
                parkingAmountCalculateRequestDto.setCarStyle(buildDto.getCarStyle());
                parkingAmountCalculateRequestDto.setStartTime(buildDto.getStartTime());
                parkingAmountCalculateRequestDto.setEndTime(now);
                ObjectResultDto<CalculateAmountResultDto> parkingAmountCalculateResult =
                        calculateIntegrationService.calculateAmount(parkingAmountCalculateRequestDto);
                if (parkingAmountCalculateResult.isSuccess() && parkingAmountCalculateResult.getData() != null) {
                    CalculateAmountResultDto parkingAmountCalculate = parkingAmountCalculateResult.getData();
                    //单位换算为元
                    payableAmount = NumberUtils.amountFen2Yuan(parkingAmountCalculate.getAmount());
                }
            } else {
                //单位换算为元
                payableAmount = NumberUtils.amountFen2Yuan(buildDto.getActualPayAmount());
            }
            if (buildDto.getChargeMode().equals(ChargeModeEnum.BEFORE.getValue())) {
                //先缴费后离场
                paymentInfo(buildDto, parkingOrderViewResultDto, payableAmount);
            } else if (buildDto.getChargeMode().equals(ChargeModeEnum.AFTER.getValue())) {
                //先离场后缴费
                //不允许支付
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
//                parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        } else {
            //已经离场
            //停车时长
            long timeMillis = buildDto.getEndTime().getTime() - buildDto.getStartTime().getTime();
            parkingOrderViewResultDto.setEndTime(DateUtils.formatDate(buildDto.getEndTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
            parkingOrderViewResultDto.setParkingLength(DateUtils.formatDateTimeChinese(timeMillis));

            //应付金额
            BigDecimal payableAmount = NumberUtils.amountFen2Yuan(buildDto.getPayableAmount());
            paymentInfo(buildDto, parkingOrderViewResultDto, payableAmount);
        }
        parkingOrderViewResultDto.setActualPayAmount(NumberUtils.amountFen2Yuan(buildDto.getActualPayAmount()).toString());
        parkingOrderViewResultDto.setStatus(buildDto.getStatus());
        parkingOrderViewResultDto.setPayStatus(buildDto.getPayStatus());
        if (buildDto.getPayTime() != null) {
            parkingOrderViewResultDto.setPayTime(DateUtils.formatDate(buildDto.getEndTime(),
                    DateUtils.DEFAULT_DATE_TIME_FORMAT));
        }
        parkingOrderViewResultDto.setPlateColor(buildDto.getPlateColor());
        parkingOrderViewResultDto.setPlateNumber(buildDto.getPlateNumber());
        parkingOrderViewResultDto.setCarStyle(buildDto.getCarStyle());
        if (buildDto.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            parkingOrderViewResultDto.setPayWay(buildDto.getPayWay());
            parkingOrderViewResultDto.setPayType(buildDto.getPayType());
        }
        return parkingOrderViewResultDto;
    }

    /**
     * 格式化金额
     *
     * @param payAmount BigDecimal
     * @return BigDecimal
     */
    private BigDecimal amountFormat(BigDecimal payAmount) {
        String amountFormat = "0.00";
        String regex = NumberUtils.formatAmountYuan(payAmount);
        if (amountFormat.equals(regex)) {
            return BigDecimal.ZERO;
        }
        return payAmount;
    }

    /**
     * 构建支付相关信息
     *
     * @param parkingOrder              ParkingOrderViewBuildDto
     * @param parkingOrderViewResultDto ParkingOrderViewResultDto
     * @param payableAmount             BigDecimal
     */
    private void paymentInfo(ParkingOrderResultDto parkingOrder,
                             InspectParkingOrderResultDto parkingOrderViewResultDto, BigDecimal payableAmount) {
        //如果未支付，则可以支付
        if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
            //金额大于0则可以支付
            if (payableAmount.compareTo(BigDecimal.ZERO) > 0) {
                parkingOrderViewResultDto.setPayable(Boolean.TRUE);
//                    parkingOrderViewResultDto.setNeedPay(Boolean.TRUE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            } else {
                parkingOrderViewResultDto.setPayable(Boolean.FALSE);
//                    parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
                parkingOrderViewResultDto.setPayableAmount(NumberUtils.formatAmountYuan(payableAmount));
            }
        } else {
            //已经支付，则无需支付
            parkingOrderViewResultDto.setPayable(Boolean.FALSE);
//                parkingOrderViewResultDto.setNeedPay(Boolean.FALSE);
            //单位换算为元
            parkingOrderViewResultDto.setPayableAmount(NumberUtils.amountFen2Yuan(parkingOrder.getPayableAmount()).toString());
            parkingOrderViewResultDto.setActualPayAmount(NumberUtils.amountFen2Yuan(parkingOrder.getActualPayAmount()).toString());
        }
    }

    /**
     * 停车账单支付
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    @Override
    public ResultDto payParkingOrderInspect(@FluentValid(InspectUpdateParkingOrderRequestDtoValidator.class) InspectUpdateParkingOrderRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //支付方式校验
            PayWayEnum payWayEnum = PayWayEnum.valueOf(requestDto.getPayWay());
            if (payWayEnum == null) {
                return resultDto.makeResult(PayResultEnum.PAY_WAY_NOT_SUPPORT.getValue(), PayResultEnum.PAY_WAY_NOT_SUPPORT.getComment());
            }
            //获取停车账单
            ParkingOrderGetRequestDto parkingOrderGetRequestDto = new ParkingOrderGetRequestDto();
            parkingOrderGetRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = inspectParkingOrderService.getParkingOrder(parkingOrderGetRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_ORDER_NOT_FOUND.getValue(), PmsResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            }
            ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();

            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getInspectPaymentParkingOrderLockKey(parkingOrder.getOrderNo()));
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
                    //实际支付用户ID(待确定)
                    Long userId = null;
                    Date now = DateUtils.now();
                    //平台订单准备支付
                    ParkingOrderPreparePaymentRequestDto parkingOrderPreparePaymentRequestDto = new ParkingOrderPreparePaymentRequestDto();
                    parkingOrderPreparePaymentRequestDto.setOrderNo(parkingOrder.getOrderNo());
                    parkingOrderPreparePaymentRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                    parkingOrderPreparePaymentRequestDto.setRecordNo(parkingOrder.getRecordNo());
                    parkingOrderPreparePaymentRequestDto.setPayedUserId(userId);
                    parkingOrderPreparePaymentRequestDto.setPayTime(now);
                    parkingOrderPreparePaymentRequestDto.setPayWay(payWayEnum.getValue());
                    parkingOrderPreparePaymentRequestDto.setPayType(PayTypeEnum.OTHER.getValue());
                    parkingOrderPreparePaymentRequestDto.setPayAmount(NumberUtils.amountYuan2FenInt(requestDto.getPaymentAmount()));
                    ObjectResultDto<ParkingOrderPreparePaymentResultDto> paymentResultDtoObject = inspectPreparePayParkingOrderService.preparePayParkingOrderInspect(parkingOrderPreparePaymentRequestDto);
                    if (paymentResultDtoObject.isFailed() || paymentResultDtoObject.getData() == null) {
                        return resultDto.makeResult(PayResultEnum.PARKING_ORDER_PAY_FAILED.getValue(),
                                PayResultEnum.PARKING_ORDER_PAY_FAILED.getComment());
                    }
                    ParkingOrderPreparePaymentResultDto preparePaymentResultDto = paymentResultDtoObject.getData();
                    needPay = preparePaymentResultDto.getNeedPay();
                    //支付金额
                    BigDecimal payAmount = NumberUtils.amountFen2Yuan(preparePaymentResultDto.getPayableAmount());
                    if (needPay) {
                        if ((payAmount.compareTo(requestDto.getPaymentAmount()) != 0)) {
                            return resultDto.makeResult(PayResultEnum.ORDER_AMOUNT_ERROR.getValue(),
                                    PayResultEnum.ORDER_AMOUNT_ERROR.getComment()
                            );
                        }
                        return inspectParkingOrderService.updateParkingOrder(requestDto);

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
            log.error("更新停车订单失败" + e.getMessage());
            return resultDto.makeResult(OrderResultEnum.PARKING_ORDER_UPDATE_ERR.getValue(), OrderResultEnum.PARKING_ORDER_UPDATE_ERR.getComment());
        }
        return resultDto;
    }

    /**
     * 获取停车订单支付分布式锁键
     *
     * @param orderNo orderNo
     */

    private String getInspectPaymentParkingOrderLockKey(String orderNo) {
        return "lock:inspect.pay.parking.order_" + orderNo;
    }

}
