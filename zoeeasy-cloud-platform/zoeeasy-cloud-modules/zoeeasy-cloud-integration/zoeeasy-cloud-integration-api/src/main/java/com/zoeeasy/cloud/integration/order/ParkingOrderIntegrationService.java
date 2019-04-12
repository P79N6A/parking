package com.zoeeasy.cloud.integration.order;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.request.TerminateOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderSyncResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.TerminateOrderPlaceResultDto;
import com.zoeeasy.cloud.message.payload.OrderConfirmCallbackPayload;
import com.zoeeasy.cloud.message.payload.PaymentNotifyCallBackPayload;
import com.zoeeasy.cloud.message.payload.QueryPriceCallBackPayload;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderSettleRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSettleResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;

/**
 * 订单集成服务
 *
 * @author AkeemSuper
 * @since 2018/10/7 0007
 */
public interface ParkingOrderIntegrationService {

    /**
     * 停车账单准备支付
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ObjectResultDto<ParkingOrderPreparePaymentResultDto> preparePayParkingOrder(ParkingOrderPreparePaymentRequestDto requestDto);

    /**
     * 停车中可支付账单结算
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<ParkingOrderSettleResultDto>
     */
    ObjectResultDto<ParkingOrderSettleResultDto> settleParkingOrder(ParkingOrderSettleRequestDto requestDto);

    /**
     * 请求场库系统订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<TerminateOrderPlaceResultDto> placeTerminateParkingOrder(TerminateOrderPlaceRequestDto requestDto);

    /**
     * 账单支付回调处理
     *
     * @param orderConfirmCallbackPayload
     * @return
     * @throws BusinessException
     */
    ResultDto processOrderCallbackMessage(OrderConfirmCallbackPayload orderConfirmCallbackPayload) throws BusinessException;

    /**
     * 同步第三方平台停车场账单信息
     *
     * @param parkingOrder parkingOrder
     * @param parkingInfo  parkingInfo
     * @return ParkingOrderSyncResultDto
     */
    ObjectResultDto<ParkingOrderSyncResultDto> syncParkingOrder(ParkingOrderResultDto parkingOrder, ParkingInfoResultDto parkingInfo);

    /**
     * 同步第三方平台账单状态
     *
     * @param paymentNotifyCallBackPayload
     * @return
     */
    ResultDto syncOrderStatus(PaymentNotifyCallBackPayload paymentNotifyCallBackPayload);

    /**
     * 根据道闸查询价格返回消息更新账单
     *
     * @param payload
     * @return
     */
    ResultDto updateOrderStatus(QueryPriceCallBackPayload payload);
}
