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
import com.zoeeasy.cloud.integration.inspect.InspectPushService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/11/14 0014
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD_NOTIFY, consumerGroup =
        MessageQueueDefinitions.ConsumeGroup.INSPECT_PASS_RECORD_NOTIFY)
@Slf4j
public class PassRecordNotifyConsumer implements RocketMQListener<PassingRecordToInspectPayload> {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private InspectPushService inspectPushService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(PassingRecordToInspectPayload passingRecordToInspectPayLoad) {

        try {
            log.info("received message: {}", passingRecordToInspectPayLoad);
            if (passingRecordToInspectPayLoad != null) {
                log.debug("------[接收到要推发送巡检的过车记录消息{}]--------[开始处理时间{}]------",
                        JSON.toJSONString(passingRecordToInspectPayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                //获取锁,同一条过车消息只能被一个线程处理
                LockInfo lockInfo = new LockInfo();
                lockInfo.setType(LockType.Fair);
                lockInfo.setName(getParkRecordNotifyLockKey(passingRecordToInspectPayLoad));
                lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
                //默认3分钟
                lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
                Lock lock = lockFactory.getLock(lockInfo);
                boolean lockAcquired = false;
                try {
                    lockAcquired = lock.acquire();
                    if (lockAcquired) {
                        //根据过车记录发送巡检消息
                        inspectPushService.sendNotifyPassRecord(passingRecordToInspectPayLoad);
                    } else {
                        log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                    }
                } catch (Exception e) {
                    log.error(" 处理过车巡检队列消息失败,异常信息", e.getMessage());
                } finally {
                    if (lockAcquired) {
                        lock.release();
                    }
                }
                log.debug("------[接收到要推发送巡检的过车记录消息{}]--------[结束处理时间{}]------", JSON.toJSONString(passingRecordToInspectPayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            }
        } catch (Exception e) {
            log.error("接收到要推发送巡检的过车记录消息处理失败" + e.getMessage(), e);
            //放入处理失败消息队列
            RocketMessage<PassingRecordToInspectPayload> messageFail = new RocketMessage<>();
            messageFail.setDestination(MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD_NOTIFY_FAIL);
            messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            messageFail.setMessageId(StringUtils.getUUID());
            messageFail.setPayload(passingRecordToInspectPayLoad);
            messageSendService.sendAndSaveSync(messageFail);
        }
    }

    /**
     * 获取停车记录分布式锁键
     *
     * @param parkingRecordPayLoad parkingRecordPayLoad
     * @return
     */
    private String getParkRecordNotifyLockKey(PassingRecordToInspectPayload parkingRecordPayLoad) {

        return new StringBuilder().append("lock:zoeeasy.passing.to_inspect_notify_").
                append(parkingRecordPayLoad.getParkingId()).append("_").
                append(parkingRecordPayLoad.getParkingLotId()).append("_").
                append(parkingRecordPayLoad.getPassingNo()).append("_").toString();
    }
}
