package com.zhuyitech.parking.tool.service.api;

import com.zhuyitech.parking.tool.dto.request.push.BatchPushMessageRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushAllMessageRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushMessageTestRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushSingleUserMessageRequestDto;

/**
 * 消息通知推送服务降级
 *
 * @author walkman
 */
public class PushNotificationServiceMock implements PushNotificationService {

    /**
     * 单个用户消息推送
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushSingleUser(PushSingleUserMessageRequestDto requestDto) {
    }

    /**
     * 推送停车入场消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushParkingEnterNotification(PushSingleUserMessageRequestDto requestDto) {
    }

    /**
     * 全平台全用户推送
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushAll(PushAllMessageRequestDto requestDto) {
    }

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushNewParkingOrderNotification(PushSingleUserMessageRequestDto requestDto) {
    }

    /**
     * 推送停车账单产生消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushNewParkingOrderNotification(BatchPushMessageRequestDto requestDto) {
    }

    /**
     * 推送停车账单支付成功消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushParkingOrderPayedNotification(PushSingleUserMessageRequestDto requestDto) {
    }

    /**
     * 推送限行提醒消息
     *
     * @param requestDto requestDto
     */
    @Override
    public void pushTrafficLimitHintNotification(BatchPushMessageRequestDto requestDto) {
    }

    /**
     * 消息推送测试 requestDto
     *
     * @param requestDto requestDto
     */
    @Override
    public void testPushMessage(PushMessageTestRequestDto requestDto) {
    }
}
