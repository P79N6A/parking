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
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 平台过车消息队列处理
 *
 * @author AkeemSuper
 * @date 2018/9/25 0025
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.PASSING_VEHICLE_FUSHANG,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.PASSING_VEHICLE_FUSHANG_CONSUMEGROUP)
@Slf4j
public class PassingVehicleMessageFuShangConsumer implements RocketMQListener<PassingVehiclePayload> {

    @Autowired
    private PassingVehicleIntegrationService passIntegrationService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(PassingVehiclePayload passingVehiclePayload) {
        if (passingVehiclePayload != null) {
            log.info("------[接收到要处理的过车消息{}]--------[开始处理时间{}]------", JSON.toJSONString(passingVehiclePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPassVehicleLockKey(passingVehiclePayload));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //根据记录处理成过车记录
                    passIntegrationService.processPassingVehicleRecord(passingVehiclePayload);
                } else {
                    log.error("过车消息处理获取分布式锁时抛错");
                }
            } catch (Exception e) {
                log.error("过车消息处理失败,异常信息:{}", e.getMessage());
                //放入处理失败消息队列
                RocketMessage<PassingVehiclePayload> messageFail = new RocketMessage<>();
                messageFail.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE_FUSHANG_FAIL);
                messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                messageFail.setMessageId(StringUtils.getUUID());
                messageFail.setPayload(passingVehiclePayload);
                messageSendService.sendAndSaveSync(messageFail);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.info("------[接收到要处理的过车消息{}]--------[结束处理时间{}]------", JSON.toJSONString(passingVehiclePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param passVehicle orderNo
     * @return
     */
    private String getPassVehicleLockKey(PassingVehiclePayload passVehicle) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.fushang.pass_").
                        append(passVehicle.getPassingUuid()).append("_").
                        append(passVehicle.getParkCode()).append("_").
                        append(passVehicle.getPlateNumber()).append("_").
                        append(passVehicle.getDirect()).toString();
    }
}
