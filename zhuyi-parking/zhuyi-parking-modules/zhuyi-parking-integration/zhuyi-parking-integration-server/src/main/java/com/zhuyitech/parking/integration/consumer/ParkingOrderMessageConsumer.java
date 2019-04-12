package com.zhuyitech.parking.integration.consumer;

import com.alibaba.fastjson.JSON;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zhuyitech.parking.integration.service.api.PushIntegrationService;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PushSingleParkingPayload;
import lombok.extern.slf4j.Slf4j;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 停车入场消息订阅
 *
 * @author walkman
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.PUSH_PARKING_RECORD,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.PUSH_PARKING_RECORD)
@Slf4j
public class ParkingOrderMessageConsumer implements RocketMQListener<PushSingleParkingPayload> {

    @Autowired
    private PushIntegrationService pushIntegrationService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(PushSingleParkingPayload pushSingleParkingPayload) {

        try {

            if (pushSingleParkingPayload != null) {
                log.debug("------[接收到要处理的推送单用户停车消息{}]--------[开始处理时间{}]------", JSON.toJSONString(pushSingleParkingPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                //获取锁,同一条过车消息只能被一个线程处理
                LockInfo lockInfo = new LockInfo();
                lockInfo.setType(LockType.Fair);
                lockInfo.setName(getPassVehicleLockKey(pushSingleParkingPayload));
                lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
                //默认3分钟
                lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
                Lock lock = lockFactory.getLock(lockInfo);
                boolean lockAcquired = false;
                try {
                    lockAcquired = lock.acquire();
                    if (lockAcquired) {
                        //根据海康记录处理成过车记录
                        pushIntegrationService.processParkingPayload(pushSingleParkingPayload);
                    }
                } catch (Exception e) {
                    log.error("获取分布式锁时抛错：{}", e.getMessage());
                } finally {
                    if (lockAcquired) {
                        lock.release();
                    }
                }
                log.debug("------[接收到要处理的推送单用户停车消息{}]--------[结束处理时间{}]------", JSON.toJSONString(pushSingleParkingPayload),
                        DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            }
            //手动消息应答
        } catch (Exception e) {
            log.error("推送单用户停车消息处理失败" + e.getMessage(), e);
            //放入处理失败消息队列
            RocketMessage<PushSingleParkingPayload> messageFail = new RocketMessage<>();
            messageFail.setDestination(MessageQueueDefinitions.Topic.PUSH_PARKING_RECORD_FAIL);
            messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            messageFail.setMessageId(StringUtils.getUUID());
            messageFail.setPayload(pushSingleParkingPayload);
            messageSendService.sendAndSaveSync(messageFail);
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param passVehicle orderNo
     * @return
     */
    private String getPassVehicleLockKey(PushSingleParkingPayload passVehicle) {

        return
                new StringBuilder().append("lock:parking.push_single_parking_").
                        append(passVehicle.getPlateNumber()).append("_").
                        append(passVehicle.getPlateColor()).append("_").
                        append(passVehicle.getPlateNumber()).append("_").
                        append(passVehicle.getMessageType()).toString();
    }
}
