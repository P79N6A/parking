package com.zhuyitech.parking.integration.service.impl;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.integration.service.api.PushIntegrationService;
import com.zhuyitech.parking.tool.dto.request.notification.NotificationSendRequestDto;
import com.zhuyitech.parking.tool.dto.request.push.PushSingleUserMessageRequestDto;
import com.zhuyitech.parking.tool.enums.PushMessageType;
import com.zhuyitech.parking.tool.service.api.NotificationService;
import com.zhuyitech.parking.tool.service.api.PushNotificationService;
import com.zhuyitech.parking.ucc.car.UserCarInfoService;
import com.zhuyitech.parking.ucc.car.request.CarOwnerListGetRequestDto;
import com.zhuyitech.parking.ucc.car.result.CarOwnerGetResultDto;
import com.zoeeasy.cloud.message.payload.PushSingleParkingPayload;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 推送集成服务
 *
 * @author walkman
 */
@Service("pushIntegrationService")
@Slf4j
public class PushIntegrationServiceImpl implements PushIntegrationService {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserCarInfoService userCarInfoService;

    /**
     * 处理停车推送消息
     *
     * @param pushSingleParkingPayload pushSingleParkingPayload
     * @return
     */
    @Override
    public ResultDto processParkingPayload(PushSingleParkingPayload pushSingleParkingPayload) {
        ResultDto resultDto = new ResultDto();
        if (pushSingleParkingPayload == null || StringUtils.isEmpty(pushSingleParkingPayload.getMessageType())) {
            return resultDto.success();
        }
        try {

            //停车入场或者新账单产生
            if (pushSingleParkingPayload.getMessageType().equals(PushMessageType.PARKING_ENTER.getCode())
                    || pushSingleParkingPayload.getMessageType().equals(PushMessageType.NEW_ORDER.getCode())) {

                CarOwnerListGetRequestDto carOwnerGetRequestDto = new CarOwnerListGetRequestDto();
                carOwnerGetRequestDto.setPlateNumber(pushSingleParkingPayload.getPlateNumber());
                carOwnerGetRequestDto.setPlateColor(pushSingleParkingPayload.getPlateColor());
                ListResultDto<CarOwnerGetResultDto> carOwnerResultDtoList = userCarInfoService.getCarOwnerList(carOwnerGetRequestDto);
                if (carOwnerResultDtoList.isFailed() || CollectionUtils.isEmpty(carOwnerResultDtoList.getItems())) {
                    return resultDto.success();
                } else {

                    for (CarOwnerGetResultDto carOwnerGetResultDto : carOwnerResultDtoList.getItems()) {

                        if (pushSingleParkingPayload.getMessageType().equals(PushMessageType.PARKING_ENTER.getCode())) {
                            //停车入场推送消息
                            PushSingleUserMessageRequestDto pushSingleUserMessageRequestDto = new PushSingleUserMessageRequestDto();
                            pushSingleUserMessageRequestDto.setMessageType(pushSingleParkingPayload.getMessageType());
                            pushSingleUserMessageRequestDto.setUserId(carOwnerGetResultDto.getUserId());
                            pushSingleUserMessageRequestDto.setParameters(pushSingleParkingPayload.getParameters());
                            pushSingleUserMessageRequestDto.setData(pushSingleParkingPayload.getData());
                            pushNotificationService.pushParkingEnterNotification(pushSingleUserMessageRequestDto);
                        } else if (pushSingleParkingPayload.getMessageType().equals(PushMessageType.NEW_ORDER.getCode())) {

                            //停车新账单推送消息
                            PushSingleUserMessageRequestDto pushSingleUserMessageRequestDto = new PushSingleUserMessageRequestDto();
                            pushSingleUserMessageRequestDto.setMessageType(pushSingleParkingPayload.getMessageType());
                            pushSingleUserMessageRequestDto.setUserId(carOwnerGetResultDto.getUserId());
                            pushSingleUserMessageRequestDto.setParameters(pushSingleParkingPayload.getParameters());
                            pushSingleUserMessageRequestDto.setData(pushSingleParkingPayload.getData());
                            pushNotificationService.pushNewParkingOrderNotification(pushSingleUserMessageRequestDto);

                            //发送新账单通知消息
                            NotificationSendRequestDto notificationSendRequestDto = new NotificationSendRequestDto();
                            notificationSendRequestDto.setUserId(carOwnerGetResultDto.getUserId());
                            notificationSendRequestDto.setParameters(pushSingleParkingPayload.getParameters());
                            notificationSendRequestDto.setData(pushSingleParkingPayload.getData());
                            notificationService.sendNewParkingOrderNotification(notificationSendRequestDto);
                        }
                    }
                    resultDto.success();
                }
            }
        } catch (Exception e) {
            log.error("消息推送失败:{}", e.getMessage());
        }
        return resultDto;
    }

}
