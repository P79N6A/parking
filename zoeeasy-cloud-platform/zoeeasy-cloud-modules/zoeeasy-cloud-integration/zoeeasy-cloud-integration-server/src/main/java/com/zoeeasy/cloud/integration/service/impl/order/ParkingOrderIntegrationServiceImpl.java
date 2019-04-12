package com.zoeeasy.cloud.integration.service.impl.order;

import cn.hutool.core.convert.Convert;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.NumberUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.collect.core.CollectOperateService;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyRequestDto;
import com.zoeeasy.cloud.core.enums.ChargeModeEnum;
import com.zoeeasy.cloud.core.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import com.zoeeasy.cloud.integration.open.TerminateParkingOrderService;
import com.zoeeasy.cloud.integration.open.dto.request.AnyParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.request.TerminateOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderSyncResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.TerminateOrderPlaceResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.message.payload.OrderConfirmCallbackPayload;
import com.zoeeasy.cloud.message.payload.PaymentNotifyCallBackPayload;
import com.zoeeasy.cloud.message.payload.QueryPriceCallBackPayload;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.enums.ThirdOrderSourceTypeEnum;
import com.zoeeasy.cloud.order.enums.ThirdOrderSyncStatusEnum;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.hikvision.dto.request.AnyParkingOrderSaveRequestDto;
import com.zoeeasy.cloud.order.hikvision.dto.request.FindThirdOrderByBillNoRequestDto;
import com.zoeeasy.cloud.order.hikvision.dto.request.HikvisionParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.order.hikvision.dto.request.HikvisionPaymentCompleteRequestDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.FindThirdOrderByBillNoResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderPlaceResultDto;
import com.zoeeasy.cloud.order.parking.PlatformParkingOrderService;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSettleResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.PlatformProcessService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordQueryByRecordNoRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingRecordResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 订单服务
 *
 * @author AkeemSuper
 * @date 2018/10/8 0008
 */
@Service(value = "parkingOrderIntegrationService")
@Slf4j
public class ParkingOrderIntegrationServiceImpl implements ParkingOrderIntegrationService {

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private PlatformParkingOrderService platformParkingOrderService;

    @Autowired
    private ThirdParkingOrderService thirdParkingOrderService;

    @Autowired
    private TerminateParkingOrderService terminateParkingOrderService;

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private CollectOperateService collectOperateService;

    @Autowired
    private PlatformProcessService platformProcessService;

    /**
     * 停车账单准备支付
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<ParkingOrderPreparePaymentResultDto> preparePayParkingOrder(ParkingOrderPreparePaymentRequestDto requestDto) {
        ObjectResultDto<ParkingOrderPreparePaymentResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderPreparePaymentResultDto preparePaymentResultDto = new ParkingOrderPreparePaymentResultDto();
            ParkingOrderGetPlateOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetPlateOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
            parkingOrderGetByOrderNoRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByPlateOrderNo(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
            } else {
                ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                //判断支付状态：只有在订单已创建和等待支付才允许支付
                PayStatusEnum payStatusEnum = PayStatusEnum.parse(parkingOrder.getPayStatus());
                boolean allowPayStatus = payStatusEnum.getValue().equals(PayStatusEnum.PAY_WAITING.getValue()) ||
                        payStatusEnum.getValue().equals(PayStatusEnum.CREATED.getValue());
                if (payStatusEnum == null || !allowPayStatus) {
                    return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getValue(),
                            OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getComment()
                    );
                }
                ParkingOrderPayingUpdateRequestDto parkingOrderUpdate = new ParkingOrderPayingUpdateRequestDto();
                //应付金额
                Integer payableAmount = parkingOrder.getPayableAmount();
                Date now = DateUtils.now();
                //如果未结算，则先去结算
                if (!parkingOrder.getSettle()) {
                    ParkingOrderSettleResultDto parkingOrderSettleResultDto = settleParkingOrder(parkingOrder);
                    if (parkingOrderSettleResultDto == null) {
                        //结算失败，不允许支付
                        return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getValue(),
                                OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getComment()
                        );
                    }
                    //结算成功，更新订单结算信息
                    parkingOrderUpdate.setSettleAmount(parkingOrderSettleResultDto.getSettleAmount());
                    parkingOrderUpdate.setFreeLength(parkingOrderSettleResultDto.getFreeDuration());
                    parkingOrderUpdate.setChargeLength(parkingOrderSettleResultDto.getChargeDuration());
                    parkingOrderUpdate.setSettleTime(now);
                    //更新结算金额
                    payableAmount = parkingOrderSettleResultDto.getSettleAmount();

                    //同步第三方账单
                    ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
                    parkingInfoGetRequestDto.setParkingId(parkingOrder.getParkingId());
                    ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
                    if (parkingInfoResultDto.isSuccess() && parkingInfoResultDto.getData() != null) {
                        ParkingInfoResultDto parkingInfo = parkingInfoResultDto.getData();
                        if (ChargeModeEnum.BEFORE.getValue().equals(parkingInfo.getChargeMode())
                                && (StringUtils.isNotEmpty(parkingInfo.getLocalCode()) || StringUtils.isNotEmpty(parkingInfo.getHikParkId()))) {
                            ObjectResultDto<ParkingOrderSyncResultDto> parkingOrderSyncObjectResultDto = syncParkingOrder(parkingOrder, parkingInfo);
                            if (parkingOrderSyncObjectResultDto.isFailed()) {
                                return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getValue(),
                                        OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getComment()
                                );
                            }
                            ParkingOrderSyncResultDto parkingOrderSyncResultDto = parkingOrderSyncObjectResultDto.getData();
                            //更新第三账单信息
                            parkingOrderUpdate.setThirdBillNo(parkingOrderSyncResultDto.getThirdBillNo());
                            parkingOrderUpdate.setThirdSourceType(parkingOrderSyncResultDto.getThirdBillSourceType());
                            parkingOrderUpdate.setThirdSyncStatus(parkingOrderSyncResultDto.getThirdBillSyncStatus());
                            if (parkingOrderSyncResultDto.getTotalCost() != null) {
                                parkingOrderUpdate.setPayableAmount(parkingOrderSyncResultDto.getTotalCost());
                            }
                            if (parkingOrderSyncResultDto.getParkingLength() != null) {
                                parkingOrderUpdate.setParkingLength(parkingOrderSyncResultDto.getParkingLength().longValue());
                            }
                        }
                    }
                }
                //无需支付
                if (payableAmount.compareTo(0) <= 0) {
                    parkingOrderUpdate.setNeedPay(Boolean.FALSE);
                    parkingOrderUpdate.setFreePayReason("限时免费");
                    //自动支付完成
                    if (!parkingOrder.getPayStatus().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                        parkingOrderUpdate.setPayable(Boolean.FALSE);
                        parkingOrderUpdate.setPayableAmount(0);
                        parkingOrderUpdate.setActualPayAmount(0);
                        parkingOrderUpdate.setPayStatus(PayStatusEnum.PAY_SUCCESS.getValue());
                        parkingOrderUpdate.setPayTime(now);
                    }
                    //无需支付才标记为结算完成，否则等支付完成处理为结算完成
                    parkingOrderUpdate.setSettle(Boolean.TRUE);
                } else {
                    parkingOrderUpdate.setNeedPay(Boolean.TRUE);
                    parkingOrderUpdate.setPayable(Boolean.TRUE);
                    parkingOrderUpdate.setPayableAmount(payableAmount);
                    parkingOrderUpdate.setPayStatus(PayStatusEnum.PAY_WAITING.getValue());
                    parkingOrderUpdate.setPayTime(requestDto.getPayTime());
                }
                parkingOrderUpdate.setPayedUserId(requestDto.getPayedUserId());
                parkingOrderUpdate.setPayWay(requestDto.getPayWay());
                parkingOrderUpdate.setPayType(requestDto.getPayType());
                parkingOrderUpdate.setOrderNo(parkingOrder.getOrderNo());
                parkingOrderUpdate.setPlateNumber(parkingOrder.getPlateNumber());
                platformParkingOrderService.updateParkingOrder(parkingOrderUpdate);

                preparePaymentResultDto.setNeedPay(parkingOrderUpdate.getNeedPay());
                preparePaymentResultDto.setPayable(parkingOrderUpdate.getPayable());
                preparePaymentResultDto.setPayableAmount(parkingOrderUpdate.getPayableAmount());
                objectResultDto.setData(preparePaymentResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停车账单准备支付失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 停车中可支付账单结算
     *
     * @param requestDto ParkingOrderSettleRequestDto
     * @return ParkingOrderSettleResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<ParkingOrderSettleResultDto> settleParkingOrder(ParkingOrderSettleRequestDto requestDto) {
        ObjectResultDto<ParkingOrderSettleResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment()
                );
            } else {
                ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                //判断支付状态
                PayStatusEnum payStatusEnum = PayStatusEnum.parse(parkingOrder.getPayStatus());
                if (payStatusEnum == null || payStatusEnum.getValue().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getValue(),
                            OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getComment()
                    );
                }
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("停车中可支付账单结算失败" + e.getMessage());
            return objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 订单结算
     *
     * @param parkingOrder
     */
    private ParkingOrderSettleResultDto settleParkingOrder(ParkingOrderResultDto parkingOrder) {
        //只有在停中允许支付结算并且未结算的订单才允许结算
        if (!parkingOrder.getSettle() && parkingOrder.getPayable()
                && ParkingStatusEnum.PARKING.getValue().equals(parkingOrder.getStatus())) {
            Date now = DateUtils.now();
            ParkingAmountCalculateRequestDto calculateAmountRequestDto = new ParkingAmountCalculateRequestDto();
            calculateAmountRequestDto.setParkingId(parkingOrder.getParkingId());
            calculateAmountRequestDto.setStartTime(parkingOrder.getStartTime());
            calculateAmountRequestDto.setEndTime(now);
            calculateAmountRequestDto.setCarStyle(parkingOrder.getCarStyle());
            calculateAmountRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
            ObjectResultDto<CalculateAmountResultDto> calculateAmount = calculateIntegrationService.calculateAmount(calculateAmountRequestDto);
            if (null == calculateAmount.getData() || calculateAmount.isFailed()) {
                log.error("停车订单{}结算金额计算失败:{}", parkingOrder.getOrderNo(), calculateAmount.getMessage());
                return null;
            }
            CalculateAmountResultDto calculateAmountData = calculateAmount.getData();
            //订单结算信息
            ParkingOrderSettleResultDto parkingOrderSettleResultDto = new ParkingOrderSettleResultDto();
            parkingOrderSettleResultDto.setChargeDuration(calculateAmountData.getChargeDuration());
            parkingOrderSettleResultDto.setFreeDuration(calculateAmountData.getFreeDuration());
            parkingOrderSettleResultDto.setSettleAmount(calculateAmountData.getAmount());
            return parkingOrderSettleResultDto;
        }
        return null;
    }

    /**
     * 请求场库系统订单
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<TerminateOrderPlaceResultDto> placeTerminateParkingOrder(TerminateOrderPlaceRequestDto requestDto) {
        ObjectResultDto<TerminateOrderPlaceResultDto> resultDto = new ObjectResultDto<>();
        return resultDto;
    }

    /**
     * 同步第三方平台停车场账单信息
     *
     * @param parkingOrder parkingOrder
     * @param parkingInfo  parkingInfo
     */
    @Override
    public ObjectResultDto<ParkingOrderSyncResultDto> syncParkingOrder(ParkingOrderResultDto parkingOrder, ParkingInfoResultDto parkingInfo) {
        ObjectResultDto<ParkingOrderSyncResultDto> resultDto = new ObjectResultDto<>();
        try {
            ParkingOrderSyncResultDto parkingOrderSyncResultDto = new ParkingOrderSyncResultDto();
            if (parkingInfo.getChargeMode().equals(ChargeModeEnum.BEFORE.getValue())) {
                if (StringUtils.isNotEmpty(parkingInfo.getHikParkId())) {
                    //海康道闸系统
                    HikvisionParkingOrderPlaceRequestDto placeHikParkingPaymentRequestDto = new HikvisionParkingOrderPlaceRequestDto();
                    placeHikParkingPaymentRequestDto.setTenantId(parkingOrder.getTenantId());
                    placeHikParkingPaymentRequestDto.setParkingId(parkingOrder.getParkingId());
                    placeHikParkingPaymentRequestDto.setRecordNo(parkingOrder.getRecordNo());
                    placeHikParkingPaymentRequestDto.setOrderNo(parkingOrder.getOrderNo());
                    placeHikParkingPaymentRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                    placeHikParkingPaymentRequestDto.setPlateColor(parkingOrder.getPlateColor());
                    ObjectResultDto<HikvisionParkingOrderPlaceResultDto> placeResult = thirdParkingOrderService.placeHikvisionParkingOrder(placeHikParkingPaymentRequestDto);
                    if (placeResult.isFailed() || placeResult.getData() == null) {
                        //TODO
                        return null;
                    }
                    HikvisionParkingOrderPlaceResultDto hikvisionParkingOrderPlaceResultDto = placeResult.getData();
                    parkingOrderSyncResultDto.setCostTime(hikvisionParkingOrderPlaceResultDto.getCostTime());
                    parkingOrderSyncResultDto.setParkingLength(hikvisionParkingOrderPlaceResultDto.getParkingLength());
                    parkingOrderSyncResultDto.setThirdBillNo(hikvisionParkingOrderPlaceResultDto.getHikBillNo());
                    parkingOrderSyncResultDto.setThirdBillSourceType(ThirdOrderSourceTypeEnum.HIKVISION.getValue());
                    parkingOrderSyncResultDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CREATED.getValue());
                    parkingOrderSyncResultDto.setTotalCost(hikvisionParkingOrderPlaceResultDto.getTotalCost());

                } else if (StringUtils.isNotEmpty(parkingInfo.getLocalCode())) {
                    //任意停车道闸系统
                    AnyParkingOrderPlaceRequestDto anyOrderCreateRequestDto = new AnyParkingOrderPlaceRequestDto();
                    anyOrderCreateRequestDto.setCarType(parkingOrder.getCarStyle());
                    anyOrderCreateRequestDto.setTenantId(parkingInfo.getTenantId());
                    anyOrderCreateRequestDto.setParkingId(parkingInfo.getId());
                    anyOrderCreateRequestDto.setPlateColor(parkingOrder.getPlateColor());
                    anyOrderCreateRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                    anyOrderCreateRequestDto.setOrderNo(parkingOrder.getOrderNo());
                    anyOrderCreateRequestDto.setRecordNo(parkingOrder.getRecordNo());
                    ResultDto anyParkingOrder = this.createAnyParkingOrder(anyOrderCreateRequestDto);
                    parkingOrderSyncResultDto.setThirdBillSourceType(ThirdOrderSourceTypeEnum.ANYPARKING.getValue());
                    if (anyParkingOrder.isSuccess()) {
                        parkingOrderSyncResultDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CREATING.getValue());
                        parkingOrderSyncResultDto.setThirdBillNo("");
                    } else {
                        parkingOrderSyncResultDto.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.UNCREATED.getValue());
                        parkingOrderSyncResultDto.setThirdBillNo("");
                    }
                }
                resultDto.setData(parkingOrderSyncResultDto);
                resultDto.success();
            }
        } catch (Exception ex) {
            log.error("同步第三方账单异常" + ex.getMessage());
            resultDto.failed();
        }

        return resultDto;
    }

    /**
     * 请求任意停车账单
     *
     * @param requestDto
     * @return
     */
    private ResultDto createAnyParkingOrder(AnyParkingOrderPlaceRequestDto requestDto) {
        AnyParkingOrderPlaceRequestDto anyParkingOrderPlaceRequestDto = new AnyParkingOrderPlaceRequestDto();
        anyParkingOrderPlaceRequestDto.setPlateColor(requestDto.getPlateColor());
        anyParkingOrderPlaceRequestDto.setPlateNumber(requestDto.getPlateNumber());
        anyParkingOrderPlaceRequestDto.setCarType(requestDto.getCarType());
        anyParkingOrderPlaceRequestDto.setParkingId(requestDto.getParkingId());
        anyParkingOrderPlaceRequestDto.setOrderNo(requestDto.getOrderNo());
        return terminateParkingOrderService.placeAynParkingOrder(anyParkingOrderPlaceRequestDto);
    }

    /**
     * 账单支付回调处理
     *
     * @param payload
     */
    @Override
    public ResultDto processOrderCallbackMessage(OrderConfirmCallbackPayload payload) throws BusinessException {
        ResultDto resultDto = new ResultDto();
        try {
            FindThirdOrderByBillNoRequestDto findThirdOrderByBillNoRequestDto = new FindThirdOrderByBillNoRequestDto();
            findThirdOrderByBillNoRequestDto.setBillNo(payload.getThirdBillNo());
            ObjectResultDto<FindThirdOrderByBillNoResultDto> findThirdOrderByBillNoResultDto = thirdParkingOrderService.findByBillNo(findThirdOrderByBillNoRequestDto);
            if (findThirdOrderByBillNoResultDto.isSuccess() && null != findThirdOrderByBillNoResultDto.getData()) {
                FindThirdOrderByBillNoResultDto thirdOrder = findThirdOrderByBillNoResultDto.getData();
                ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
                parkingOrderGetByOrderNoRequestDto.setOrderNo(thirdOrder.getOrderNo());
                ObjectResultDto<ParkingOrderResultDto> parkingOrderObjectResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
                if (parkingOrderObjectResultDto.isFailed() || parkingOrderObjectResultDto.getData() == null) {
                    log.error("根据OrderNo查询ParkingOrder异常" + thirdOrder.getBillNo());
                    resultDto.failed();
                }
                ParkingOrderResultDto parkingOrderResultDto = parkingOrderObjectResultDto.getData();
                if (parkingOrderResultDto.getThirdBillSourceType().equals(ThirdOrderSourceTypeEnum.ANYPARKING.getValue())) {
                    PaymentNotifyRequestDto paymentNotifyRequestDto = new PaymentNotifyRequestDto();
                    ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
                    parkingInfoGetRequestDto.setParkingId(payload.getParkingId());
                    //parkingInfoGetRequestDto.setStatus(StatusEnum.AVAILABLE.getValue());
                    ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
                    if (parkingInfoResultDto.isSuccess() && parkingInfoResultDto.getData() != null) {
                        paymentNotifyRequestDto.setLocalCode(parkingInfoResultDto.getData().getLocalCode());
                    } else {
                        throw new Exception("停车场异常" + payload.getParkingId());
                    }
                    paymentNotifyRequestDto.setParkingOrderNo(payload.getThirdBillNo());
                    paymentNotifyRequestDto.setPayAmount(payload.getActualPayAmount().toString());
                    paymentNotifyRequestDto.setPayTime(DateUtils.formatDateTime(payload.getSucceedPayTime()));
                    paymentNotifyRequestDto.setPlateNumber(thirdOrder.getPlateNumber());
                    ResultDto paymentNotifyResultDto = collectOperateService.paymentNotify(paymentNotifyRequestDto);
                    if (paymentNotifyResultDto.isSuccess()) {
                        resultDto.success();
                    } else {
                        log.error("支付通知第三方异常" + paymentNotifyResultDto.toString());
                        resultDto.failed();
                    }
                } else if (parkingOrderResultDto.getThirdBillSourceType().equals(ThirdOrderSourceTypeEnum.HIKVISION.getValue())) {
                    HikvisionPaymentCompleteRequestDto completeParkingPaymentRequestDto = new HikvisionPaymentCompleteRequestDto();
                    completeParkingPaymentRequestDto.setBillNo(payload.getThirdBillNo());
                    //海康平台订单金额要求一致(单位分)
                    completeParkingPaymentRequestDto.setPayAmount(findThirdOrderByBillNoResultDto.getData().getTotalCost());
                    //支付时间
                    completeParkingPaymentRequestDto.setPayTime(payload.getSucceedPayTime());
                    //支付方式 账户余额（云平台的账户）
                    completeParkingPaymentRequestDto.setPayType(11);
                    if (payload.getPayWay() != null) {
                        if (payload.getPayWay().equals(PayWayEnum.ALIPAY.getValue())) {
                            completeParkingPaymentRequestDto.setPayType(4);
                        } else if (payload.getPayWay().equals(PayWayEnum.WEIXINPAY.getValue())) {
                            completeParkingPaymentRequestDto.setPayType(5);
                        }
                    }
                    resultDto = thirdParkingOrderService.completeHikvisionParkingOrder(completeParkingPaymentRequestDto);

                    if (resultDto.isSuccess()) {
                        //订单已同步
                        ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdate = new ParkingOrderPayStatusUpdateRequestDto();
                        parkingOrderUpdate.setOrderNo(thirdOrder.getOrderNo());
                        parkingOrderUpdate.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CONFIRM.getValue());
                        platformParkingOrderService.updateParkingOrder(parkingOrderUpdate);
                    }
                }
            }
        } catch (Exception e) {
            log.error("账单支付回调处理失败:" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 同步第三方账单状态
     *
     * @param paymentNotifyCallBackPayload
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto syncOrderStatus(PaymentNotifyCallBackPayload paymentNotifyCallBackPayload) {
        ResultDto resultDto = new ResultDto();
        try {
            FindThirdOrderByBillNoRequestDto findThirdOrderByBillNoRequestDto = new FindThirdOrderByBillNoRequestDto();
            findThirdOrderByBillNoRequestDto.setBillNo(paymentNotifyCallBackPayload.getParkingOrderNo());
            ObjectResultDto<FindThirdOrderByBillNoResultDto> findThirdOrderByBillNoResultDto = thirdParkingOrderService.findByBillNo(findThirdOrderByBillNoRequestDto);
            if (findThirdOrderByBillNoResultDto.isSuccess() && findThirdOrderByBillNoResultDto.getData() != null) {
                FindThirdOrderByBillNoResultDto thirdOrder = findThirdOrderByBillNoResultDto.getData();
                ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
                parkingOrderGetByOrderNoRequestDto.setOrderNo(thirdOrder.getOrderNo());
                ObjectResultDto<ParkingOrderResultDto> parkingOrderObjectResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
                if (parkingOrderObjectResultDto.isSuccess() && parkingOrderObjectResultDto.getData() != null) {
                    ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdate = new ParkingOrderPayStatusUpdateRequestDto();
                    parkingOrderUpdate.setOrderNo(thirdOrder.getOrderNo());
                    parkingOrderUpdate.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CONFIRM.getValue());
                    platformParkingOrderService.updateParkingOrder(parkingOrderUpdate);
                } else {
                    log.error("根据OrderNo查询ParkingOrder异常:" + thirdOrder.getOrderNo());
                    resultDto.failed();
                }
            } else {
                log.error("根据BillNo查询ThirdParkingOrder异常:" + paymentNotifyCallBackPayload.getParkingOrderNo());
                resultDto.failed();
            }
        } catch (Exception e) {
            log.error("同步第三方账单状态失败：{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 根据道闸查询价格返回消息更新账单
     *
     * @param payload
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateOrderStatus(QueryPriceCallBackPayload payload) {
        ResultDto resultDto = new ResultDto();
        try {
            AnyParkingOrderSaveRequestDto anyParkingOrderSaveRequestDto = new AnyParkingOrderSaveRequestDto();
            anyParkingOrderSaveRequestDto.setBillNo(payload.getParkingOrderNo());
            anyParkingOrderSaveRequestDto.setAmount(NumberUtils.amountYuan2FenInt(payload.getPrice()));
            anyParkingOrderSaveRequestDto.setParkingLength(payload.getDuration().longValue());
            anyParkingOrderSaveRequestDto.setEnterTime(Convert.toDate(payload.getPassInTime()));
            anyParkingOrderSaveRequestDto.setCostTime(payload.getCostTime());
            anyParkingOrderSaveRequestDto.setThirdOrderStatus(ThirdOrderSyncStatusEnum.CREATED.getValue());
            //根据orderNo查询parking_order
            ParkingOrderGetByOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetByOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(payload.getOrderNo());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = platformParkingOrderService.getByOrderNo(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isSuccess() && parkingOrderResultDto.getData() != null) {
                ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                anyParkingOrderSaveRequestDto.setOrderNo(parkingOrder.getOrderNo());
                anyParkingOrderSaveRequestDto.setParkingId(parkingOrder.getParkingId());
                anyParkingOrderSaveRequestDto.setPlateColor(parkingOrder.getPlateColor());
                anyParkingOrderSaveRequestDto.setPlateNo(parkingOrder.getPlateNumber());
                anyParkingOrderSaveRequestDto.setRecordNo(parkingOrder.getRecordNo());
                anyParkingOrderSaveRequestDto.setTenantId(parkingOrder.getTenantId());

                ParkingRecordQueryByRecordNoRequestDto parkingRecordQueryByRecordNoRequestDto = new ParkingRecordQueryByRecordNoRequestDto();
                parkingRecordQueryByRecordNoRequestDto.setRecordNo(parkingOrder.getRecordNo());
                ObjectResultDto<ParkingRecordResultDto> parkingRecord = platformProcessService.getParkingRecordByRecordNo(parkingRecordQueryByRecordNoRequestDto);
                if (parkingRecord.isSuccess() && parkingRecord.getData() != null) {
                    anyParkingOrderSaveRequestDto.setCarType(parkingRecord.getData().getCarType());
                }
                thirdParkingOrderService.saveAnyParkingOrder(anyParkingOrderSaveRequestDto);

                ParkingOrderPayStatusUpdateRequestDto parkingOrderUpdate = new ParkingOrderPayStatusUpdateRequestDto();
                parkingOrderUpdate.setOrderNo(parkingOrder.getOrderNo());
                parkingOrderUpdate.setThirdBillSyncStatus(ThirdOrderSyncStatusEnum.CREATED.getValue());
                parkingOrderUpdate.setPayableAmount(NumberUtils.amountYuan2FenInt(payload.getPrice()));
                parkingOrderUpdate.setParkingLength((long) (payload.getDuration() * 60));
                parkingOrderUpdate.setThirdBillNo(payload.getParkingOrderNo());
                platformParkingOrderService.updateParkingOrder(parkingOrderUpdate);
                resultDto.success();
            } else {
                log.error("订单查询失败");
                resultDto.failed();
            }
        } catch (Exception e) {
            log.error("根据道闸查询价格返回消息更新账单失败：{}", e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
