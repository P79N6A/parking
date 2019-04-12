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
import com.zoeeasy.cloud.integration.pass.PassingVehicleIntegrationService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.MagneticPassingPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/21 0021
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.MAGNETIC_PASSING,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.MAGNETIC_PASSING)
@Slf4j
public class MagneticMessageConsumer implements RocketMQListener<MagneticPassingPayload> {

    @Autowired
    private PassingVehicleIntegrationService passIntegrationService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(MagneticPassingPayload magneticMessagePayLoad) {

        log.info("received message: {}", magneticMessagePayLoad);
        if (magneticMessagePayLoad != null) {
            log.debug("------[接收到要处理的地磁过车消息{}]--------[开始处理时间{}]------", JSON.toJSONString(magneticMessagePayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPassVehicleLockKey(magneticMessagePayLoad));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //根据海康记录处理成过车记录
                    passIntegrationService.processMagneticVehicleRecord(magneticMessagePayLoad);
                } else {
                    log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("地磁过车消息处理失败," + e.getMessage(), e);
                //放入处理失败消息队列
                RocketMessage<MagneticPassingPayload> messageFail = new RocketMessage<>();
                messageFail.setDestination(MessageQueueDefinitions.Topic.MAGNETIC_PASSING_FAIL);
                messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                messageFail.setMessageId(StringUtils.getUUID());
                messageFail.setPayload(magneticMessagePayLoad);
                messageSendService.sendAndSaveSync(messageFail);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到要处理的地磁过车消息{}]--------[结束处理时间{}]------", JSON.toJSONString(magneticMessagePayLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param passVehicle orderNo
     * @return
     */
    private String getPassVehicleLockKey(MagneticPassingPayload passVehicle) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.magnetic.pass_").
                        append(passVehicle.getParkingId()).append("_").
                        append(passVehicle.getParkingLotId()).append("_").
                        append(passVehicle.getPassingType()).append("_").
                        append(passVehicle.getPassTime()).toString();
    }
}
