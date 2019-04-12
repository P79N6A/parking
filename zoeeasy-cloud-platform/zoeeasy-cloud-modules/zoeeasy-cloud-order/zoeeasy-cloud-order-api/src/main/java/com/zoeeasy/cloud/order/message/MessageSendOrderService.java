package com.zoeeasy.cloud.order.message;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.message.dot.request.OrderConfirmCallbackRequestDto;

/**
 * @author AkeemSuper
 * @date 2018/12/23 0023
 */
public interface MessageSendOrderService {
    /**
     * 发送三方账单支付回调消息
     *
     * @param requestDto
     * @return
     */
    ResultDto sendOrderConfirmCallbackMessage(OrderConfirmCallbackRequestDto requestDto);
}
