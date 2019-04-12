package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.integration.appoint.dto.request.PaySuccessNotificationSendRequestDto;
import com.zoeeasy.cloud.integration.message.MessageSendIntegrationService;
import com.zoeeasy.cloud.integration.message.dto.request.CustomerPaySuccessSendRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.HikImageMessageSendRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.MagneticPassMessageRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.MessageSendUserPayOrderRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.ParkingEnterPushMessageRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.ParkingOrderPushMessageRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.PassRecordMessageSendRequestDto;
import com.zoeeasy.cloud.integration.message.dto.request.PassRecordNotifySendRequestDto;
import com.zoeeasy.cloud.integration.message.validator.PassRecordMessageSendRequestDtoValidator;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.CustomerPaySuccessPayload;
import com.zoeeasy.cloud.message.payload.HikPassingImageFetchPayload;
import com.zoeeasy.cloud.message.payload.MagneticPassingPayload;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;
import com.zoeeasy.cloud.message.payload.PaySuccessNotificationPayload;
import com.zoeeasy.cloud.message.payload.PushSingleParkingPayload;
import com.zoeeasy.cloud.message.payload.RechargeSuccessPayload;
import com.zoeeasy.cloud.message.payload.UserPayOrderPayload;
import com.zoeeasy.cloud.notify.enums.PushMessageType;
import com.zoeeasy.cloud.pay.trade.dto.request.order.RechargeSuccessRequestDto;
import com.zoeeasy.cloud.pay.trade.validator.RechargeSuccessRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 集成模块消息发送
 *
 * @author zwq
 */
@Service(value = "messageSendIntegrationService")
@Slf4j
public class MessageSendIntegrationServiceImpl implements MessageSendIntegrationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 发送充值成功消息
     *
     * @param requestDto RechargeSuccessRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendRechargeSuccessMessage(@FluentValid(RechargeSuccessRequestDtoValidator.class) RechargeSuccessRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<RechargeSuccessPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.RECHARGE_SUCCESS);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            RechargeSuccessPayload rechargeSuccessPayLoad = modelMapper.map(requestDto, RechargeSuccessPayload.class);
            message.setPayload(rechargeSuccessPayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("发送充值成功消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 会员支付成功消息发送
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto sendCustomerPaySuccessMessage(CustomerPaySuccessSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<CustomerPaySuccessPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.CLOUD_TRADE_CUSTOMER_PAY_SUCCESS);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            CustomerPaySuccessPayload customerPaySuccessPayload = modelMapper.map(requestDto, CustomerPaySuccessPayload.class);
            message.setPayload(customerPaySuccessPayload);
            return messageSendService.sendAndSaveSync(message);
        } catch (Exception e) {
            log.error("会员支付成功消息发送失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送账单支付成功推送通知与消息
     *
     * @param requestDto PaySuccessNotificationSendRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendPaySuccessNotification(PaySuccessNotificationSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PaySuccessNotificationPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.PAY_SUCCESS_NOTIFY);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            PaySuccessNotificationPayload paySuccessNotificationPayLoad = modelMapper.map(requestDto, PaySuccessNotificationPayload.class);
            message.setPayload(paySuccessNotificationPayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("发送账单支付成功推送通知与消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送钱包支付消息
     *
     * @param requestDto MessageSendUserPayOrderRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendUserPayOrderMessage(MessageSendUserPayOrderRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<UserPayOrderPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.USER_PAY_ORDER);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            UserPayOrderPayload userPayOrderPayLoad = modelMapper.map(requestDto, UserPayOrderPayload.class);
            message.setPayload(userPayOrderPayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("发送钱包支付消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送地磁过车消息
     *
     * @param requestDto MagneticPassMessageRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendMagneticMessage(MagneticPassMessageRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<MagneticPassingPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.MAGNETIC_PASSING);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            MagneticPassingPayload magneticMessagePayLoad = new MagneticPassingPayload();
            magneticMessagePayLoad.setParkingId(requestDto.getParkingId());
            magneticMessagePayLoad.setParkingLotId(requestDto.getParkingLotId());
            magneticMessagePayLoad.setPassingType(requestDto.getPassingType());
            magneticMessagePayLoad.setPassTime(requestDto.getPassTime());
            message.setPayload(magneticMessagePayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("发送地磁过车消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 入车巡检推送
     *
     * @param requestDto PassRecordMessageSendRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendInspectEnterPushMessage(@FluentValid(PassRecordMessageSendRequestDtoValidator.class) PassRecordMessageSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PassingRecordToInspectPayload> message = new RocketMessage<>();
//            message.setTxProduceGroup(MessageQueueDefinitions.TxProducerGroup.INSPECT_ENTER_PUSH);
            message.setDestination(MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            PassingRecordToInspectPayload passingRecordToInspectPayLoad = modelMapper.map(requestDto, PassingRecordToInspectPayload.class);
            message.setPayload(passingRecordToInspectPayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("停车消息发送巡检服务失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 过车记录巡检消息
     *
     * @param requestDto PassRecordNotifySendRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendPassRecordNotify(PassRecordNotifySendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PassingRecordToInspectPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.INSPECT_PASS_RECORD_NOTIFY);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            PassingRecordToInspectPayload passingRecordToInspectPayLoad = modelMapper.map(requestDto, PassingRecordToInspectPayload.class);
            message.setPayload(passingRecordToInspectPayLoad);
            messageSendService.sendAndSaveSync(message);
            resultDto.success();
        } catch (Exception e) {
            log.error("停车消息发送巡检服务失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送入场停车推送消息
     *
     * @param requestDto ParkingEnterPushMessageRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendParkingEnterPushMessage(ParkingEnterPushMessageRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PushSingleParkingPayload> message = new RocketMessage<>();
//            message.setTxProduceGroup(MessageQueueDefinitions.TxProducerGroup.PARKING_ENTER_PUSH);
            message.setDestination(MessageQueueDefinitions.Topic.PUSH_PARKING_RECORD);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            PushSingleParkingPayload payload = new PushSingleParkingPayload();
            payload.setPlateColor(requestDto.getPlateColor());
            payload.setPlateNumber(requestDto.getPlateNumber());
            payload.setMessageType(PushMessageType.PARKING_ENTER.getCode());
            Map<Object, Object> mapNotification = new HashMap<>();
            mapNotification.put("orderDate", DateUtils.formatDate(requestDto.getStartTime(), DateUtils.YYYY_MM_DD_HH_MM_CHINESE));
            mapNotification.put("parkingName", requestDto.getParkingName());
            JSONObject jsonObjectNotification = new JSONObject();
            jsonObjectNotification.put("orderId", requestDto.getOrderId());
            jsonObjectNotification.put("orderNo", requestDto.getOrderNo());
            payload.setParameters(mapNotification);
            payload.setData(jsonObjectNotification);
            message.setPayload(payload);
            messageSendService.sendAndSaveSync(message);
            log.info("------[发送停车入场消息]--------[消息内容{}]------", JSON.toJSONString(message));
            resultDto.success();
        } catch (Exception e) {
            log.error("发送入场停车推送消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送停车新账单推送消息
     *
     * @param requestDto ParkingOrderPushMessageRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendParkingOrderPushMessage(ParkingOrderPushMessageRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<PushSingleParkingPayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.PUSH_PARKING_RECORD);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            PushSingleParkingPayload payload = new PushSingleParkingPayload();
            payload.setPlateColor(requestDto.getPlateColor());
            payload.setPlateNumber(requestDto.getPlateNumber());
            payload.setMessageType(PushMessageType.NEW_ORDER.getCode());
            Map<Object, Object> mapNotification = new HashMap<>();
            mapNotification.put("orderDate", DateUtils.formatDate(requestDto.getStartTime(), DateUtils.YYYY_MM_DD_HH_MM_CHINESE));
            mapNotification.put("parkingName", requestDto.getParkingName());
            JSONObject jsonObjectNotification = new JSONObject();
            jsonObjectNotification.put("orderId", requestDto.getOrderId());
            jsonObjectNotification.put("orderNo", requestDto.getOrderNo());
            payload.setParameters(mapNotification);
            payload.setData(jsonObjectNotification);
            message.setPayload(payload);
            messageSendService.sendAndSaveSync(message);
            log.info("------[推送新账单通知消息]--------[消息内容{}]------", JSON.toJSONString(message));
            resultDto.success();
        } catch (Exception e) {
            log.error("发送入场停车推送消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 发送海康平台过车图像获取消息
     *
     * @param requestDto HikImageMessageSendRequestDto
     * @return ResultDto
     */
    @Override
    public ResultDto sendHikImageMessage(HikImageMessageSendRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            RocketMessage<HikPassingImageFetchPayload> message = new RocketMessage<>();
//            message.setTxProduceGroup(MessageQueueDefinitions.TxProducerGroup.HIKVISION_PASSING_IMAGE);
            message.setDestination(MessageQueueDefinitions.Topic.HIKVISION_PASSING_IMAGE);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            HikPassingImageFetchPayload payload = modelMapper.map(requestDto, HikPassingImageFetchPayload.class);
            message.setPayload(payload);
            messageSendService.sendAndSaveSync(message);
            log.info("------[推送海康过车图像获取消息]--------[消息内容{}]------", JSON.toJSONString(message));
            resultDto.success();
        } catch (Exception e) {
            log.error("推送海康过车图像获取消息失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
