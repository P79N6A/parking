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
import com.zoeeasy.cloud.message.payload.AnyPassRecordImagePayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author guigu
 * @date 2018/03/26 0002ANY_PARKING_PASSING_RECORD_IMAGE
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.PASSING_VEHICLE_FUSHANG_IMAGE,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.PASSING_VEHICLE_IMAGE_FUSHANG_CONSUMEGROUP)
@Slf4j
public class FuShangRecordImageMessageConsumer implements RocketMQListener<AnyPassRecordImagePayload> {

    @Autowired
    private PassingVehicleIntegrationService passIntegrationService;

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(AnyPassRecordImagePayload anyPassRecordImagePayload) {

        if (anyPassRecordImagePayload != null) {
            log.info("received message: {}", anyPassRecordImagePayload);
            log.debug("------[接收到任意停车平台图像消息{}]--------[开始处理时间{}]------",
                    JSON.toJSONString(anyPassRecordImagePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPassVehicleLockKey(anyPassRecordImagePayload));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认2分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 2);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    passIntegrationService.anyParkingRecordImageProcess(anyPassRecordImagePayload);
                } else {
                    log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("接收到任意停车平台图像消息处理失败" + e.getMessage(), e);
                //处理失败重新放入队列
                RocketMessage<AnyPassRecordImagePayload> messageFail = new RocketMessage<>();
                messageFail.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE_FUSHANG_IMAGE_FAIL);
                messageFail.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                messageFail.setMessageId(StringUtils.getUUID());
                messageFail.setPayload(anyPassRecordImagePayload);
                messageSendService.sendAndSaveOrderlySync(messageFail);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到任意停车平台图像消息{}]--------[结束处理时间{}]------", JSON.toJSONString(anyPassRecordImagePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param passVehicle orderNo
     * @return
     */
    private String getPassVehicleLockKey(AnyPassRecordImagePayload passVehicle) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.fushang.parking_image_").
                        append(passVehicle.getPassingCode()).toString();
    }
}
