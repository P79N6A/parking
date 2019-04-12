package com.zhuyitech.parking.ucc.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.lock.redisson.core.Lock;
import com.scapegoat.infrastructure.lock.redisson.core.LockFactory;
import com.scapegoat.infrastructure.lock.redisson.core.LockInfo;
import com.scapegoat.infrastructure.lock.redisson.enumerate.LockType;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zhuyitech.parking.tool.dto.request.notification.NotificationSendRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushSingleUserMessageRequestDto;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import com.zhuyitech.parking.tool.service.api.PushNotificationService;
import com.zhuyitech.parking.ucc.service.api.AccountTransactionService;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.PaySuccessNotificationPayload;
import com.scapegoat.boot.starter.rocketmq.annotation.RocketMQMessageListener;
import com.scapegoat.boot.starter.rocketmq.core.RocketMQListener;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zwq
 * @date 2018/10/17 0021
 */
@Service
@RocketMQMessageListener(topic = "any_pay_success_notification_message", consumerGroup = "zoeeasy_pay_success_notification_message_consumer")
@Slf4j
public class PaySuccessNotificationMessageCustomer implements RocketMQListener<PaySuccessNotificationPayload> {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private LockFactory lockFactory;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onMessage(PaySuccessNotificationPayload paySuccessNotificationLoad) {

        try {
            log.info("received message: {}", paySuccessNotificationLoad);
            if (paySuccessNotificationLoad != null) {
                log.debug("------[接收到支付成功发送推送消息请求{}]--------[开始处理时间{}]------", JSON.toJSONString(paySuccessNotificationLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
                //获取锁,同一条过车消息只能被一个线程处理
                LockInfo lockInfo = new LockInfo();
                lockInfo.setType(LockType.Fair);
                lockInfo.setName(getAppointPacketPayLockKey(paySuccessNotificationLoad));
                lockInfo.setWaitTime(LockInfo.DEFAULT_LOCK_WAIT_TIME);
                //默认3分钟
                lockInfo.setLeaseTime(LockInfo.DEFAULT_LOCK_LEASE_TIME * 3L);
                Lock lock = lockFactory.getLock(lockInfo);
                boolean lockAcquired = false;
                try {
                    lockAcquired = lock.acquire();
                    if (lockAcquired) {
                        NotificationSendRequestDto notificationSendRequestDto = new NotificationSendRequestDto();
                        Map<Object, Object> mapNotification = new HashMap<>();
                        mapNotification.put("orderDate", DateUtils.formatDate(paySuccessNotificationLoad.getStartTime(), DateUtils.YYYY_MM_DD_HH_MM_CHINESE));
                        mapNotification.put("parkingName", paySuccessNotificationLoad.getParkingName());
                        JSONObject jsonObjectNotification = new JSONObject();
                        jsonObjectNotification.put("orderId", paySuccessNotificationLoad.getOrderId());
                        jsonObjectNotification.put("orderNo", paySuccessNotificationLoad.getOrderNo());
                        jsonObjectNotification.put("amount", paySuccessNotificationLoad.getPayableAmount());
                        notificationSendRequestDto.setParameters(mapNotification);
                        notificationSendRequestDto.setData(jsonObjectNotification);
                        notificationSendRequestDto.setUserId(paySuccessNotificationLoad.getPayedUserId());
                        notificationService.sendParkingOrderPayedNotification(notificationSendRequestDto);

                        //发送推送消息
                        PushSingleUserMessageRequestDto pushSingleUserMessageRequestDto = new PushSingleUserMessageRequestDto();
                        pushSingleUserMessageRequestDto.setUserId(paySuccessNotificationLoad.getPayedUserId());
                        Map<Object, Object> mapMessage = new HashMap<>();
                        mapMessage.put("orderDate", DateUtils.formatDate(paySuccessNotificationLoad.getStartTime(), DateUtils.YYYY_MM_DD_HH_MM_CHINESE));
                        mapMessage.put("parkingName", paySuccessNotificationLoad.getParkingName());
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("orderId", paySuccessNotificationLoad.getOrderId());
                        jsonObject.put("orderNo", paySuccessNotificationLoad.getOrderNo());
                        jsonObject.put("amount", paySuccessNotificationLoad.getPayableAmount());
                        pushSingleUserMessageRequestDto.setParameters(mapMessage);
                        pushSingleUserMessageRequestDto.setData(jsonObject);
                        pushNotificationService.pushParkingOrderPayedNotification(pushSingleUserMessageRequestDto);
                    }
                } catch (Exception e) {
                    log.error("获取分布式锁时抛错：{}", e.getMessage());
                } finally {
                    if (lockAcquired) {
                        lock.release();
                    }
                }
                log.debug("------[接收到支付成功发送推送消息请求{}]--------[结束处理时间{}]------", JSON.toJSONString(paySuccessNotificationLoad), DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss SSS"));
            }
        } catch (Exception e) {
            log.error("支付成功发送推送消息请求处理失败" + e.getMessage(), e);
            //放入处理失败消息队列
            RocketMessage<PaySuccessNotificationPayload> messageFail = new RocketMessage<>();
            messageFail.setDestination("zoeeasy_pay_success_notification_message_fail");
            messageFail.setSender("INTEGRATION");
            messageFail.setMessageId(StringUtils.getUUID());
            messageFail.setPayload(paySuccessNotificationLoad);
            messageSendService.sendAndSaveSync(messageFail);
        }
    }

    /**
     * 获取用户订单分布式锁键
     *
     * @return
     */
    private String getAppointPacketPayLockKey(PaySuccessNotificationPayload paySuccessNotificationLoad) {

        return
                new StringBuilder().append("lock:user.pay.order.operate_").
                        append(paySuccessNotificationLoad.getOrderId()).append("_").
                        append(paySuccessNotificationLoad.getOrderNo()).append("_").
                        append(paySuccessNotificationLoad.getParkingName()).append("_").
                        append(paySuccessNotificationLoad.getPayableAmount()).append("_").
                        append(paySuccessNotificationLoad.getPayedUserId()).append("_").
                        append(paySuccessNotificationLoad.getStartTime()).append("_").toString();
    }
}
