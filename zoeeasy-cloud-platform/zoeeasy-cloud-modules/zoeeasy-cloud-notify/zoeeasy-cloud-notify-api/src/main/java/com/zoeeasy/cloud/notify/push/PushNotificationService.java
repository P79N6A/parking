package com.zoeeasy.cloud.notify.push;

import com.zoeeasy.cloud.notify.push.dto.request.*;

/**
 * 消息通知推送服务
 *
 * @author walkman
 */
public interface PushNotificationService {

    /**
     * 单个用户消息推送
     *
     * @param requestDto requestDto
     */
    void pushSingleUser(PushSingleUserMessageRequestDto requestDto);

    /**
     * 通过tag批量推送
     *
     * @param requestDto requestDto
     */
    void pushBatchByTag(PushBatchByTagRequestDto requestDto);

    /**
     * 推送停车入场消息
     *
     * @param requestDto requestDto
     */
    void pushParkingEnterNotification(PushSingleUserMessageRequestDto requestDto);

    /**
     * 全平台全用户推送
     *
     * @param requestDto requestDto
     */
    void pushAll(PushAllMessageRequestDto requestDto);

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    void pushNewParkingOrderNotification(PushSingleUserMessageRequestDto requestDto);

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    void pushNewParkingOrderNotification(BatchPushMessageRequestDto requestDto);

    /**
     * 推送停车账单支付成功消息
     *
     * @param requestDto requestDto
     */
    void pushParkingOrderPayedNotification(PushSingleUserMessageRequestDto requestDto);

    /**
     * 推送限行提醒消息
     *
     * @param requestDto requestDto
     */
    void pushTrafficLimitHintNotification(BatchPushMessageRequestDto requestDto);

    /**
     * 消息推送测试 requestDto
     *
     * @param requestDto requestDto
     */
    void testPushMessage(PushMessageTestRequestDto requestDto);

    /**
     * 推送巡检消息
     *
     * @param requestDto
     */
    void pushPassInspectNotification(PushPassInspectNotifyRequestDto requestDto);

}
