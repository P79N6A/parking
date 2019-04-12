package com.zhuyitech.parking.integration.trade;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentConfirmRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.ParkingOrderPaymentRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.PaymentCheckRequestDto;
import com.zhuyitech.parking.integration.order.dto.result.ParkingOrderPaymentResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentCheckResultDto;
import com.zhuyitech.parking.integration.order.dto.result.PaymentConfirmResultDto;

/**
 * 交易集成服务
 *
 * @author walkman
 */
public interface TradeIntegrationService {

    /**
     * 停车账单支付
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<ParkingOrderPaymentResultDto> payParkingOrder(ParkingOrderPaymentRequestDto requestDto);

    /**
     * 停车账单支付确认
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<PaymentConfirmResultDto> confirmParkingOrderPayment(ParkingOrderPaymentConfirmRequestDto requestDto);

    /**
     * 停车账单支付检查
     *
     * @param requestDto 请求参数
     * @return
     */
    ObjectResultDto<PaymentCheckResultDto> payCheck(PaymentCheckRequestDto requestDto);
}
