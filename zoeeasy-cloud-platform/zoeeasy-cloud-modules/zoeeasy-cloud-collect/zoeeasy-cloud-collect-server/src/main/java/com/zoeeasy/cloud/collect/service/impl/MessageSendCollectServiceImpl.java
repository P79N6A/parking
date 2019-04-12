package com.zoeeasy.cloud.collect.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.collect.core.MessageSendCollectService;
import com.zoeeasy.cloud.collect.dto.request.PaymentNotifyCallBackRequestDto;
import com.zoeeasy.cloud.collect.dto.request.QueryPriceCallBackRequestDto;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PaymentNotifyCallBackPayload;
import com.zoeeasy.cloud.message.payload.QueryPriceCallBackPayload;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Inmier
 * @date 2019-03-21
 */
@Slf4j
@Service("messageSendCollectService")
public class MessageSendCollectServiceImpl implements MessageSendCollectService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSendService messageSendService;

    @Override
    public ResultDto sendPaymentNotifyCallBackMessage(PaymentNotifyCallBackRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PaymentNotifyCallBackPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.THIRD_PAYMENT_NOTIFY_CALL_BACK);
            message.setSender(MessageQueueDefinitions.Sender.COLLECT);
            message.setMessageId(StringUtils.getUUID());
            PaymentNotifyCallBackPayload paymentNotifyCallBackPayload = modelMapper.map(requestDto, PaymentNotifyCallBackPayload.class);
            message.setPayload(paymentNotifyCallBackPayload);
            return messageSendService.sendAndSaveSync(message);
        } catch (Exception e) {
            log.error("发送支付通知结果处理消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public ResultDto sendQueryPriceCallBackMessage(QueryPriceCallBackRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<QueryPriceCallBackPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.THIRD_PAYMENT_NOTIFY_CALL_BACK);
            message.setSender(MessageQueueDefinitions.Sender.COLLECT);
            message.setMessageId(StringUtils.getUUID());
            QueryPriceCallBackPayload queryPriceCallBackPayload = modelMapper.map(requestDto, QueryPriceCallBackPayload.class);
            message.setPayload(queryPriceCallBackPayload);
            return messageSendService.sendAndSaveSync(message);
        } catch (Exception e) {
            log.error("发送支付通知结果处理消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
