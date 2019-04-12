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
 * @date 2018/10/17 0017
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD, consumerGroup =
        MessageQueueDefinitions.ConsumeGroup.INSPECT_PASS_RECORD)
@Slf4j
public class PassRecordMessageConsumer implements RocketMQListener<PassingRecordToInspectPayload> {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private InspectPushService inspectPushService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(PassingRecordToInspectPayload passingRecordToInspectPayLoad) {

        if (passingRecordToInspectPayLoad != null) {
            log.debug("------[接收到要推送巡检的过车记录{}]--------[开始处理时间{}]------",
                    JSON.toJSONString(passingRecordToInspectPayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getParkRecordLockKey(passingRecordToInspectPayLoad));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //根据停车记录推送巡检
                    inspectPushService.processPushPassRecord(passingRecordToInspectPayLoad);
                } else {
                    log.error("过车消息处理获取分布式锁时抛错:{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("获取分布式锁时抛错：{}", e.getMessage());
                log.error("接收到要推送巡检的过车记录失败" + e.getMessage(), e);
                //放入处理失败消息队列
                RocketMessage<PassingRecordToInspectPayload> messageFail = new RocketMessage<>();
                messageFail.setDestination(MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD_FAIL);
                messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                messageFail.setMessageId(StringUtils.getUUID());
                messageFail.setPayload(passingRecordToInspectPayLoad);
                messageSendService.sendAndSaveSync(messageFail);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到要推送巡检的过车记录{}]--------[结束处理时间{}]------", JSON.toJSONString(passingRecordToInspectPayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取停车记录分布式锁键
     *
     * @param parkingRecordPayLoad parkingRecordPayLoad
     * @return
     */
    private String getParkRecordLockKey(PassingRecordToInspectPayload parkingRecordPayLoad) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.to_inspect_").
                        append(parkingRecordPayLoad.getParkingId()).append("_").
                        append(parkingRecordPayLoad.getParkingLotId()).append("_").
                        append(parkingRecordPayLoad.getPassingNo()).append("_").toString();
    }
}
