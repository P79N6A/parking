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
import com.zoeeasy.cloud.integration.appoint.AppointOrderPlatformIntegrationService;
import com.zoeeasy.cloud.message.payload.AppointOrderClosePayload;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCloseRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 预约订单取消
 *
 * @author zwq
 * @date 2018/11/20
 */
@Service
@RocketMQMessageListener(topic = MessageQueueDefinitions.Topic.APPOINT_ORDER_CLOSE,
        consumerGroup = MessageQueueDefinitions.ConsumeGroup.APPOINT_ORDER_CLOSE)
@Slf4j
public class AppointOrderCloseConsumer implements RocketMQListener<AppointOrderClosePayload> {

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private AppointOrderPlatformIntegrationService appointIntegrationService;

    @Override
    public void onMessage(AppointOrderClosePayload appointOrderClosePayload) {

        if (appointOrderClosePayload != null) {
            log.info("received message: {}", appointOrderClosePayload);
            log.debug("------[接收到要处理的预约订单消息{}]--------[开始处理时间{}]------", JSON.toJSONString(appointOrderClosePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            //获取锁,同一条过车消息只能被一个线程处理
            LockInfo lockInfo = new LockInfo();
            lockInfo.setType(LockType.Fair);
            lockInfo.setName(getAppointOrderCloseLockKey(appointOrderClosePayload));
            lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
            //默认3分钟
            lockInfo.setLeaseTime((long) LockInfo.DEFAULT_LOCK_LEASE_TIME * 3);
            Lock lock = lockFactory.getLock(lockInfo);
            boolean lockAcquired = false;
            try {
                lockAcquired = lock.acquire();
                if (lockAcquired) {
                    //关闭预约订单
                    AppointOrderCloseRequestDto appointOrderCloseRequestDto = new AppointOrderCloseRequestDto();
                    appointOrderCloseRequestDto.setOrderNo(appointOrderClosePayload.getOrderNo());
                    appointIntegrationService.closeAppointOrder(appointOrderCloseRequestDto);
                } else {
                    log.error("获取分布式锁时抛错：{}", lockInfo.getName());
                }
            } catch (Exception e) {
                log.error("关闭预约订单处理失败" + e.getMessage(), e);
            } finally {
                if (lockAcquired) {
                    lock.release();
                }
            }
            log.debug("------[接收到要处理的预约订单消息{}]--------[结束处理时间{}]------", JSON.toJSONString(appointOrderClosePayload), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
        }
    }

    /**
     * 关闭预约订单分布式锁键
     *
     * @param payload orderNo
     * @return
     */
    private String getAppointOrderCloseLockKey(AppointOrderClosePayload payload) {

        return
                new StringBuilder().append("lock:zoeeasy.cloud_appoint_order_close_message_").
                        append(payload.getOrderNo()).toString();
    }

}
