package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.zoeeasy.cloud.charge.chg.dto.result.CalculateAmountResultDto;
import com.zoeeasy.cloud.core.enums.ChargeModeEnum;
import com.zoeeasy.cloud.core.enums.ParkingStatusEnum;
import com.zoeeasy.cloud.core.enums.PayStatusEnum;
import com.zoeeasy.cloud.core.enums.PublicEnum;
import com.zoeeasy.cloud.integration.inspect.InspectPreparePayParkingOrderService;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderSyncResultDto;
import com.zoeeasy.cloud.integration.park.CalculateIntegrationService;
import com.zoeeasy.cloud.integration.park.dto.request.ParkingAmountCalculateRequestDto;
import com.zoeeasy.cloud.order.enums.OrderResultEnum;
import com.zoeeasy.cloud.order.enums.ThirdOrderSourceTypeEnum;
import com.zoeeasy.cloud.order.hikvision.ThirdParkingOrderService;
import com.zoeeasy.cloud.order.hikvision.dto.request.HikvisionParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderPlaceResultDto;
import com.zoeeasy.cloud.order.inspect.InspectParkingOrderService;
import com.zoeeasy.cloud.order.parking.ParkingOrderManagerService;
import com.zoeeasy.cloud.order.parking.dto.request.InspectParkingOrderUpdateRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetPlateOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSettleResultDto;
import com.zoeeasy.cloud.pms.park.ParkingInfoService;
import com.zoeeasy.cloud.pms.park.dto.request.ParkingGetRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author AkeemSuper
 * @date 2019/1/7 0007
 */
@Service("inspectPreparePayParkingOrderService")
@Slf4j
public class InspectPreparePayParkingOrderServiceImpl implements InspectPreparePayParkingOrderService {

    @Autowired
    private InspectParkingOrderService inspectParkingOrderService;

    @Autowired
    private CalculateIntegrationService calculateIntegrationService;

    @Autowired
    private ParkingInfoService parkingInfoService;

    @Autowired
    private ThirdParkingOrderService thirdParkingOrderService;

    @Autowired
    private ParkingOrderManagerService parkingOrderManagerService;

    /**
     * 停车账单准备支付
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<ParkingOrderPreparePaymentResultDto> preparePayParkingOrderInspect(ParkingOrderPreparePaymentRequestDto requestDto) {
        ObjectResultDto<ParkingOrderPreparePaymentResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            ParkingOrderPreparePaymentResultDto preparePaymentResultDto = new ParkingOrderPreparePaymentResultDto();
            ParkingOrderGetPlateOrderNoRequestDto parkingOrderGetByOrderNoRequestDto = new ParkingOrderGetPlateOrderNoRequestDto();
            parkingOrderGetByOrderNoRequestDto.setOrderNo(requestDto.getOrderNo());
            parkingOrderGetByOrderNoRequestDto.setPlateNumber(requestDto.getPlateNumber());
            ObjectResultDto<ParkingOrderResultDto> parkingOrderResultDto = inspectParkingOrderService.getByPlateOrderNoInspect(parkingOrderGetByOrderNoRequestDto);
            if (parkingOrderResultDto.isFailed() || parkingOrderResultDto.getData() == null) {
                objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_NOT_FOUND.getValue(),
                        OrderResultEnum.PARKING_ORDER_NOT_FOUND.getComment());
            } else {
                ParkingOrderResultDto parkingOrder = parkingOrderResultDto.getData();
                //判断支付状态
                //只有在未支付和等待支付才允许支付
                PayStatusEnum payStatusEnum = PayStatusEnum.parse(parkingOrder.getPayStatus());
                if (payStatusEnum == null || payStatusEnum.getValue().equals(PayStatusEnum.PAY_SUCCESS.getValue())) {
                    return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getValue(),
                            OrderResultEnum.PARKING_ORDER_PAY_STATUS_INVALID.getComment()
                    );
                }
                InspectParkingOrderUpdateRequestDto parkingOrderUpdate = new InspectParkingOrderUpdateRequestDto();
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
                    parkingOrderUpdate.setChargeLength(parkingOrderSettleResultDto.getChargeDuration());
                    parkingOrderUpdate.setFreeLength(parkingOrderSettleResultDto.getFreeDuration());
                    parkingOrderUpdate.setSettleTime(now);
                    //更新结算金额
                    payableAmount = parkingOrderSettleResultDto.getSettleAmount();

                    //获取停车场信息
                    ParkingGetRequestDto parkingGetRequestDto = new ParkingGetRequestDto();
                    parkingGetRequestDto.setId(parkingOrder.getParkingId());
                    ObjectResultDto<ParkingResultDto> parkingInfoResultDto = parkingInfoService.getParkingInfo(parkingGetRequestDto);
                    if (parkingInfoResultDto.isSuccess() && parkingInfoResultDto.getData() != null) {
                        ParkingResultDto parkingInfo = parkingInfoResultDto.getData();
                        if (ChargeModeEnum.BEFORE.getValue().equals(parkingInfo.getChargeMode())
                                && (StringUtils.isNotEmpty(parkingInfo.getLocalCode()) ||
                                StringUtils.isNotEmpty(parkingInfo.getHikParkId())
                        )) {
                            //同步第三方平台停车场信息
                            ParkingOrderSyncResultDto parkingOrderSyncResultDto = syncParkingOrder(parkingOrder, parkingInfo);
                            if (parkingOrderSyncResultDto == null) {
                                // 同步失败，不允许支付
                                return objectResultDto.makeResult(OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getValue(),
                                        OrderResultEnum.PARKING_ORDER_CANNOT_PAYED.getComment()
                                );
                            }
                            //更新第三账单信息
                            parkingOrderUpdate.setThirdBillNo(parkingOrderSyncResultDto.getThirdBillNo());
                            parkingOrderUpdate.setThirdSourceType(parkingOrderSyncResultDto.getThirdBillSourceType());
                            parkingOrderUpdate.setThirdSyncStatus(PublicEnum.YES.getValue());
                            //任意停车平台以客户端结算金额为准
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
                parkingOrderManagerService.update(parkingOrderUpdate);

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
     * 订单结算
     */
    private ParkingOrderSettleResultDto settleParkingOrder(ParkingOrderResultDto parkingOrder) {
        //只有在停中允许支付结算并且未结算的订单才允许结算
        if (!parkingOrder.getSettle() && parkingOrder.getPayable()
                && ParkingStatusEnum.PARKING.getValue().equals(parkingOrder.getStatus())) {
//            计算金额
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
     * 同步第三方平台停车场信息
     *
     * @param parkingOrder parkingOrder
     * @param parkingInfo  parkingInfo
     */
    private ParkingOrderSyncResultDto syncParkingOrder(ParkingOrderResultDto parkingOrder, ParkingResultDto parkingInfo) {

        //获取停车场信息
        if (parkingInfo.getChargeMode().equals(ChargeModeEnum.BEFORE.getValue())) {
            //停车场接入海康平台
            if (StringUtils.isNotEmpty(parkingInfo.getHikParkId())) {
                //如果是支付后离场，如果没有海康账单，则先去请求海康账单
                HikvisionParkingOrderPlaceRequestDto placeHikParkingPaymentRequestDto = new HikvisionParkingOrderPlaceRequestDto();
                placeHikParkingPaymentRequestDto.setTenantId(parkingOrder.getTenantId());
                placeHikParkingPaymentRequestDto.setParkingId(parkingOrder.getParkingId());
                placeHikParkingPaymentRequestDto.setRecordNo(parkingOrder.getRecordNo());
                placeHikParkingPaymentRequestDto.setOrderNo(parkingOrder.getOrderNo());
                placeHikParkingPaymentRequestDto.setPlateColor(parkingOrder.getPlateColor());
                placeHikParkingPaymentRequestDto.setPlateNumber(parkingOrder.getPlateNumber());
                ObjectResultDto<HikvisionParkingOrderPlaceResultDto> placeResult = thirdParkingOrderService.placeHikvisionParkingOrder(placeHikParkingPaymentRequestDto);
                if (placeResult.isFailed() || placeResult.getData() == null) {
                    //TODO
                    return null;
                }
                HikvisionParkingOrderPlaceResultDto hikvisionParkingOrderPlaceResultDto = placeResult.getData();
                ParkingOrderSyncResultDto parkingOrderSyncResultDto = new ParkingOrderSyncResultDto();
                parkingOrderSyncResultDto.setCostTime(hikvisionParkingOrderPlaceResultDto.getCostTime());
                parkingOrderSyncResultDto.setParkingLength(hikvisionParkingOrderPlaceResultDto.getParkingLength());
                parkingOrderSyncResultDto.setThirdBillNo(hikvisionParkingOrderPlaceResultDto.getHikBillNo());
                parkingOrderSyncResultDto.setThirdBillSourceType(ThirdOrderSourceTypeEnum.HIKVISION.getValue());
                parkingOrderSyncResultDto.setTotalCost(hikvisionParkingOrderPlaceResultDto.getTotalCost());
                return parkingOrderSyncResultDto;
            } else if (StringUtils.isNotEmpty(parkingInfo.getLocalCode())) {
                //停车场接入任意停车平台
                ParkingOrderSyncResultDto parkingOrderSyncResultDto = new ParkingOrderSyncResultDto();
            }
        }
        return null;
    }
}
