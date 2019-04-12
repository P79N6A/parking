package com.zoeeasy.cloud.integration.consumer;

import com.alibaba.fastjson.JSON;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.message.payload.PaymentNotifyCallBackPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Inmier
 * @date 2019-03-21
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.THIRD_PAYMENT_NOTIFY_CALL_BACK, consumerGroup =
        MessageQueueDefinitions.ConsumeGroup.THIRD_PAYMENT_NOTIFY_CALL_BACK)
@Slf4j
public class PaymentNotifyCallBackConsumer implements RocketMQListener<PaymentNotifyCallBackPayload> {

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @Override
    public void onMessage(PaymentNotifyCallBackPayload paymentNotifyCallBackPayload) {
        if (paymentNotifyCallBackPayload != null) {
            log.debug("------[接收到道闸三方账单支付回调消息{}]--------[开始处理时间{}]------",
                    JSON.toJSONString(paymentNotifyCallBackPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            try {
                parkingOrderIntegrationService.syncOrderStatus(paymentNotifyCallBackPayload);
            } catch (Exception e) {
                log.error("处理道闸三方账单支付回调消息失败,异常信息{},", e.getMessage());
            }
            log.debug("------[接收到三方账单支付回调消息{}]--------[结束处理时间{}]------", JSON.toJSONString(paymentNotifyCallBackPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

}
