package com.zoeeasy.cloud.order.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.OrderConfirmCallbackPayload;
import com.zoeeasy.cloud.order.message.MessageSendOrderService;
import com.zoeeasy.cloud.order.message.dot.request.OrderConfirmCallbackRequestDto;
import lombok.extern.log4j.Log4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @date 2018/12/23 0023
 */
@Service("messageSendOrderService")
@Log4j
public class MessageSendOrderServiceImpl implements MessageSendOrderService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 发送三方账单支付回调消息
     *
     * @param requestDto
     */
    @Override
    public ResultDto sendOrderConfirmCallbackMessage(OrderConfirmCallbackRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            if (requestDto != null) {
                RocketMessage<OrderConfirmCallbackPayload> message = new RocketMessage<>();
                message.setDestination(MessageQueueDefinitions.Topic.ORDER_CALL_BACK);
                message.setSender(MessageQueueDefinitions.Sender.ORDER);
                message.setMessageId(StringUtils.getUUID());
                OrderConfirmCallbackPayload payLoad = modelMapper.map(resultDto, OrderConfirmCallbackPayload.class);
                message.setPayload(payLoad);
                messageSendService.sendAndSaveSync(message);
                resultDto.success();
                return resultDto;
            }
            resultDto.failed();
        } catch (Exception e) {
            log.error("发送三方账单支付回调消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
