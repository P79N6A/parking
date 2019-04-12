package com.zoeeasy.cloud.integration.message;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.PaySuccessNotificationSendRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.*;
import com.zoeeasy.cloud.pay.trade.dto.request.order.RechargeSuccessRequestDto;

/**
 * 消息发送集成服务
 *
 * @author zwq
 */
public interface MessageSendIntegrationService {

    /**
     * 发送充值成功消息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto sendRechargeSuccessMessage(RechargeSuccessRequestDto requestDto);

    /**
     * 会员支付成功消息发送
     *
     * @param requestDto
     * @return
     */
    ResultDto sendCustomerPaySuccessMessage(CustomerPaySuccessSendRequestDto requestDto);

    /**
     * 发送账单支付成功推送通知与消息
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto sendPaySuccessNotification(PaySuccessNotificationSendRequestDto requestDto);

    /**
     * 修改userPayOrder消息发送
     *
     * @param requestDto 请求参数
     * @return ResultDto
     */
    ResultDto sendUserPayOrderMessage(MessageSendUserPayOrderRequestDto requestDto);

    /**
     * 发送地磁过车消息
     *
     * @param requestDto MagneticPassMessageRequestDto
     * @return
     */
    ResultDto sendMagneticMessage(MagneticPassMessageRequestDto requestDto);

    /**
     * 过车消息发送巡检推送服务
     *
     * @param requestDto PassRecordMessageSendRequestDto
     * @return ResultDto
     */
    ResultDto sendInspectEnterPushMessage(PassRecordMessageSendRequestDto requestDto);

    /**
     * 过车记录巡检消息
     *
     * @param requestDto PassRecordNotifySendRequestDto
     * @return ResultDto
     */
    ResultDto sendPassRecordNotify(PassRecordNotifySendRequestDto requestDto);

    /**
     * 发送入场停车推送消息
     *
     * @param requestDto ParkingEnterPushMessageRequestDto
     * @return ResultDto
     */
    ResultDto sendParkingEnterPushMessage(ParkingEnterPushMessageRequestDto requestDto);

    /**
     * 发送停车新账单推送消息
     *
     * @param requestDto ParkingOrderPushMessageRequestDto
     * @return ResultDto
     */
    ResultDto sendParkingOrderPushMessage(ParkingOrderPushMessageRequestDto requestDto);

    /**
     * 发送海康平台过车图像获取消息
     *
     * @param requestDto HikImageMessageSendRequestDto
     * @return ResultDto
     */
    ResultDto sendHikImageMessage(HikImageMessageSendRequestDto requestDto);


}
