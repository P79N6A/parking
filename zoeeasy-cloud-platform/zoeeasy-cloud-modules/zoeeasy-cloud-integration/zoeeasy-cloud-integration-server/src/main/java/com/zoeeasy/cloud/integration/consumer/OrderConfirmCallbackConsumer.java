package com.zoeeasy.cloud.integration.consumer;

import com.alibaba.fastjson.JSON;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.integration.order.ParkingOrderIntegrationService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.OrderConfirmCallbackPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/11/14 0014
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.ORDER_CALL_BACK, consumerGroup =
        MessageQueueDefinitions.ConsumeGroup.ORDER_CALL_BACK)
@Slf4j
public class OrderConfirmCallbackConsumer implements RocketMQListener<OrderConfirmCallbackPayload> {

    @Autowired
    private ParkingOrderIntegrationService parkingOrderIntegrationService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(OrderConfirmCallbackPayload orderConfirmCallbackPayload) {

        if (orderConfirmCallbackPayload != null) {
            log.debug("------[接收到三方账单支付回调消息{}]--------[开始处理时间{}]------",
                    JSON.toJSONString(orderConfirmCallbackPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getOrderCallbackLockKey(orderConfirmCallbackPayload));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    parkingOrderIntegrationService.processOrderCallbackMessage(orderConfirmCallbackPayload);
                } else {
                    log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("处理三方账单支付回调消息 失败,异常信息{},", e.getMessage());
                log.error("接收到三方账单支付回调消息处理失败" + e.getMessage(), e);
                //放入处理失败消息队列
                RocketMessage<OrderConfirmCallbackPayload> messageFail = new RocketMessage<>();
                messageFail.setDestination(MessageQueueDefinitions.Topic.ORDER_CALL_BACK);
                messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                messageFail.setMessageId(StringUtils.getUUID());
                messageFail.setPayload(orderConfirmCallbackPayload);
                messageSendService.sendAndSaveSync(messageFail);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到三方账单支付回调消息{}]--------[结束处理时间{}]------", JSON.toJSONString(orderConfirmCallbackPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取停车记录分布式锁键
     *
     * @param orderConfirmCallbackPayload orderConfirmCallbackPayload
     * @return
     */
    private String getOrderCallbackLockKey(OrderConfirmCallbackPayload orderConfirmCallbackPayload) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.to_inspect_notify_").
                        append(orderConfirmCallbackPayload.getParkingId()).append("_").
                        append(orderConfirmCallbackPayload.getThirdBillNo()).append("_").toString();
    }
}
