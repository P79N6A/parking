package com.zoeeasy.cloud.integration.pay;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.trade.dto.request.order.PlacePaymentOrderRequestDto;
import com.zoeeasy.cloud.pay.trade.dto.result.order.PlacePaymentOrderResultDto;

/**
 * 支付集成服务
 *
 * @author walkman
 */
public interface TradePaymentIntegrationService {

    /**
     * 支付订单下单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PlacePaymentOrderResultDto> placePaymentOrder(PlacePaymentOrderRequestDto requestDto);
}
