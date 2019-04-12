package com.zoeeasy.cloud.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQTemplate;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.domain.MessageEntity;
import com.zoeeasy.cloud.message.enums.MessagingSendStatusEnum;
import com.zoeeasy.cloud.message.service.MessageCrudService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service(value = "messageSendService")
public class MessageSendServiceImpl implements MessageSendService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private MessageCrudService messageCrudService;

    /**
     * 同步发送消息
     *
     * @param message
     */
    @Override
    public ResultDto sendSync(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        SendResult sendResult = rocketMQTemplate.syncSend(message.getDestination(), message.getPayload());
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            resultDto.success();
        } else {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 同步发送消息并保存
     *
     * @param message
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto sendAndSaveSync(RocketMessage<?> message) {
        SendResult sendResult = rocketMQTemplate.syncSend(message.getDestination(), message.getPayload());
        return saveMessage(message, sendResult);
    }

    /**
     * 同步顺序发送
     *
     * @param message
     */
    @Override
    public ResultDto sendOrderlySync(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(message.getDestination(), message.getPayload(), message.getHashKey());
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            resultDto.success();
        } else {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 同步顺序发送消息并保存
     *
     * @param message
     */
    @Override
    public ResultDto sendAndSaveOrderlySync(RocketMessage<?> message) {
        SendResult sendResult = rocketMQTemplate.syncSendOrderly(message.getDestination(), message.getPayload(), message.getHashKey());
        return saveMessage(message, sendResult);
    }

    /**
     * 异步发送
     *
     * @param message
     * @param sendCallback
     */
    @Override
    public ResultDto sendAsync(RocketMessage<?> message, SendCallback sendCallback) {
        ResultDto resultDto = new ResultDto();
        rocketMQTemplate.asyncSend(message.getDestination(), message.getPayload(), sendCallback);
        return resultDto.success();
    }

    /**
     * 异步发送并保存消息
     *
     * @param message
     * @param sendCallback
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto sendAndSaveAsync(RocketMessage<?> message, SendCallback sendCallback) {
        rocketMQTemplate.asyncSend(message.getDestination(), message.getPayload(), sendCallback);
        return saveMessage(message, null);
    }

    /**
     * 异步顺序发送
     *
     * @param message
     * @param sendCallback
     */
    @Override
    public ResultDto sendOrderlyAsync(RocketMessage<?> message, SendCallback sendCallback) {
        ResultDto resultDto = new ResultDto();
        rocketMQTemplate.asyncSendOrderly(message.getDestination(), message.getPayload(), message.getHashKey(), sendCallback);
        return resultDto.success();
    }

    /**
     * 异步顺序发送并保存消息
     *
     * @param message
     * @param sendCallback
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto sendAndSaveOrderlyAsync(RocketMessage<?> message, SendCallback sendCallback) {
        rocketMQTemplate.asyncSendOrderly(message.getDestination(), message.getPayload(), message.getHashKey(), sendCallback);
        return saveMessage(message, null);
    }

    /**
     * One-way方式发送
     *
     * @param message
     */
    @Override
    public ResultDto sendOneWay(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        rocketMQTemplate.sendOneWay(message.getDestination(), message.getPayload());
        return resultDto.success();
    }

    /**
     * One-way方式发送并保存消息
     *
     * @param message
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto sendAndSaveOneWay(RocketMessage<?> message) {
        rocketMQTemplate.sendOneWay(message.getDestination(), message.getPayload());
        return saveMessage(message, null);
    }

    /**
     * @param message
     */
    @Override
    public ResultDto sendOneWayOrderly(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        rocketMQTemplate.sendOneWayOrderly(message.getDestination(), message.getPayload(), message.getHashKey());
        return resultDto.success();
    }

    /**
     * @param message
     */
    @Override
    public ResultDto sendAndSaveOneWayOrderly(RocketMessage<?> message) {
        rocketMQTemplate.sendOneWayOrderly(message.getDestination(), message.getPayload(), message.getHashKey());
        return saveMessage(message, null);
    }

    /**
     * 发送事务消息
     *
     * @param message
     * @return
     */
    @Override
    public ResultDto sendTransactionMessage(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        SendResult sendResult = rocketMQTemplate.
                sendMessageInTransaction(message.getTxProduceGroup(), message.getDestination(), message, message.getParameter());
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            resultDto.success();
        } else {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 保存并发送事务消息
     *
     * @param message
     * @return
     */
    @Override
    public ResultDto sendAndSaveTransactionMessage(RocketMessage<?> message) {
        ResultDto resultDto = new ResultDto();
        SendResult sendResult = rocketMQTemplate.
                sendMessageInTransaction(message.getTxProduceGroup(), message.getDestination(), message, message.getParameter());
        if (SendStatus.SEND_OK.equals(sendResult.getSendStatus())) {
            resultDto = saveMessage(message, sendResult);
        } else {
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 保存消息
     *
     * @param message
     * @return
     */
    private ResultDto saveMessage(RocketMessage<?> message, SendResult sendResult) {
        ResultDto resultDto = new ResultDto();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessageId(message.getMessageId());
//        messageEntity.setTraceId(message.getTraceId());
        messageEntity.setSender(message.getSender());
        messageEntity.setExchangeTopic(message.getDestination());
//        messageEntity.setRoutingKey(message.get());
        messageEntity.setPayload(JSON.toJSONString(message.getPayload()));
        messageEntity.setMessageType(message.getMessageType());
        messageEntity.setSendStatus(MessagingSendStatusEnum.SENDING.getValue());
        if (sendResult != null) {
            messageEntity.setSendResult(JSON.toJSONString(sendResult));
        }
        messageEntity.setCreationTime(DateUtils.now());
        messageCrudService.saveMessage(messageEntity);
        return resultDto.success();
    }
}

