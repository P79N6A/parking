package com.zoeeasy.cloud.message;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import org.apache.rocketmq.client.producer.SendCallback;

/**
 * RocketMQ 消息发送服务
 *
 * @author walkman
 * @since 2018-09-18
 */
public interface MessageSendService {

    /**
     * 同步发送消息
     *
     * @param message
     */
    ResultDto sendSync(RocketMessage<?> message);

    /**
     * 同步发送消息并保存
     *
     * @param message
     */
    ResultDto sendAndSaveSync(RocketMessage<?> message);

    /**
     * 同步顺序发送
     *
     * @param message
     */
    ResultDto sendOrderlySync(RocketMessage<?> message);

    /**
     * 同步顺序发送消息并保存
     *
     * @param message
     */
    ResultDto sendAndSaveOrderlySync(RocketMessage<?> message);

    /**
     * 异步发送
     *
     * @param message
     * @param sendCallback
     */
    ResultDto sendAsync(RocketMessage<?> message, SendCallback sendCallback);

    /**
     * 异步发送并保存消息
     *
     * @param message
     * @param sendCallback
     */
    ResultDto sendAndSaveAsync(RocketMessage<?> message, SendCallback sendCallback);

    /**
     * 异步顺序发送
     *
     * @param message
     * @param sendCallback
     */
    ResultDto sendOrderlyAsync(RocketMessage<?> message, SendCallback sendCallback);

    /**
     * 异步顺序发送并保存消息
     *
     * @param message
     * @param sendCallback
     */
    ResultDto sendAndSaveOrderlyAsync(RocketMessage<?> message, SendCallback sendCallback);

    /**
     * One-way方式发送
     *
     * @param message
     */
    ResultDto sendOneWay(RocketMessage<?> message);

    /**
     * One-way方式发送并保存消息
     *
     * @param message
     */
    ResultDto sendAndSaveOneWay(RocketMessage<?> message);

    /**
     * @param message
     */
    ResultDto sendOneWayOrderly(RocketMessage<?> message);

    /**
     * @param message
     */
    ResultDto sendAndSaveOneWayOrderly(RocketMessage<?> message);

    /**
     * 发送事务消息
     *
     * @param message
     * @return
     */
    ResultDto sendTransactionMessage(RocketMessage<?> message);

    /**
     * 保存并发送事务消息
     *
     * @param message
     * @return
     */
    ResultDto sendAndSaveTransactionMessage(RocketMessage<?> message);

}
