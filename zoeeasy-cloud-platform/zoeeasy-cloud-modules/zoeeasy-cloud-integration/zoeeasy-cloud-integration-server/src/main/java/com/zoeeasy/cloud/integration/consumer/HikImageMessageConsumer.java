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
import com.zoeeasy.cloud.integration.parking.ParkingImageIntegrationService;
import com.zoeeasy.cloud.message.payload.HikPassingImageFetchPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 海康过车图像消息队列处理
 *
 * @author walkman
 * @date 2018/11/20
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.HIKVISION_PASSING_IMAGE,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.HIKVISION_PASSING_IMAGE)
@Slf4j
public class HikImageMessageConsumer implements RocketMQListener<HikPassingImageFetchPayload> {

    @Autowired
    private ParkingImageIntegrationService processHikVehicleRecord;

    @Autowired
    private LockFactory lockFactory;

    @Override
    public void onMessage(HikPassingImageFetchPayload hikPassingImageFetchPayload) {

        if (hikPassingImageFetchPayload != null) {
            log.info("received message: {}", hikPassingImageFetchPayload);
            log.debug("------[接收到要处理的海康过车图像消息{}]--------[开始处理时间{}]------", JSON.toJSONString(hikPassingImageFetchPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getPassVehicleLockKey(hikPassingImageFetchPayload));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //根据海康记录处理成过车记录
                    processHikVehicleRecord.processHikPassingImage(hikPassingImageFetchPayload);
                } else {
                    log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("海康过车消息图像处理失败" + e.getMessage(), e);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到要处理的海康过车图像消息{}]--------[结束处理时间{}]------", JSON.toJSONString(hikPassingImageFetchPayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 获取过车记录分布式锁键
     *
     * @param payload orderNo
     * @return
     */
    private String getPassVehicleLockKey(HikPassingImageFetchPayload payload) {

        return
                new StringBuilder().append("lock:zoeeasy.passing.hikvision.image_").
                        append(payload.getHikPassingUuid()).append("_").
                        append(payload.getParkCode()).toString();
    }

}
