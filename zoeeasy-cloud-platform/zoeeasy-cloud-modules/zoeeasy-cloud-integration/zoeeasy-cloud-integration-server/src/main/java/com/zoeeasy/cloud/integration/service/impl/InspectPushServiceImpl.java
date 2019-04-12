package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.base.FundamentalRequestContext;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.cst.TagPrefixConstant;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.inspect.platform.PlatformInspectService;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkCollectorInfoResultDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;
import com.zoeeasy.cloud.integration.inspect.InspectPushService;
import com.zoeeasy.cloud.integration.push.dto.request.PushTagGetInfoRequestDto;
import com.zoeeasy.cloud.integration.push.dto.result.PushTagGetInfoResultDto;
import com.zoeeasy.cloud.message.payload.PassingRecordToInspectPayload;
import com.zoeeasy.cloud.notify.cts.JPushProperty;
import com.zoeeasy.cloud.notify.enums.NotificationMessageTypeEnum;
import com.zoeeasy.cloud.notify.enums.NotificationTypeEnum;
import com.zoeeasy.cloud.notify.enums.TagTypeEnum;
import com.zoeeasy.cloud.notify.notification.NotificationService;
import com.zoeeasy.cloud.notify.notification.dto.request.NotificationSendRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.request.TemplateQueryRequestDto;
import com.zoeeasy.cloud.notify.notification.dto.result.TemplateResultDto;
import com.zoeeasy.cloud.notify.platform.PlatformNotifyService;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagAddRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.PushTagGetByParkingIdRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.request.UserPushTagSaveRequestDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagResultDto;
import com.zoeeasy.cloud.notify.platform.dto.result.PushTagSaveResultDto;
import com.zoeeasy.cloud.notify.push.PushNotificationService;
import com.zoeeasy.cloud.notify.push.dto.request.PushPassInspectNotifyRequestDto;
import com.zoeeasy.cloud.pms.enums.PmsResultEnum;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingInfoResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.platform.PlatformParkingInfoService;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByParkingIdRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author AkeemSuper
 * @date 2018/10/17 0017
 */
@Service(value = "inspectPushService")
@Slf4j
public class InspectPushServiceImpl implements InspectPushService {

    private static final String DEFAULT_NOTIFICATION_MESSAGE_TITLE = "巡检APP";

    @Autowired
    private PlatformParkingInfoService platformParkingInfoService;

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private PlatformInspectService platformInspectService;

    @Autowired
    private PlatformNotifyService platformNotifyService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private JPushProperty jpushProperty;

    /**
     * 处理过车记录推送巡检
     *
     * @param parkingRecordPayLoad
     * @return
     * @throws Exception
     */
    @Override
    public ResultDto processPushPassRecord(PassingRecordToInspectPayload parkingRecordPayLoad) {
        ResultDto resultDto = new ResultDto();
        try {
            //停车场
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(parkingRecordPayLoad.getParkingId());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoObjectResultDto = platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            if (parkingInfoObjectResultDto.isFailed() || parkingInfoObjectResultDto.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }
            ParkingInfoResultDto parkingInfo = parkingInfoObjectResultDto.getData();
            //泊位
            ParkingLotInfoGetByParkingIdRequestDto getForPassVehicleRequestDto = new ParkingLotInfoGetByParkingIdRequestDto();
            getForPassVehicleRequestDto.setParkingId(parkingRecordPayLoad.getParkingId());
            getForPassVehicleRequestDto.setParkingLotId(parkingRecordPayLoad.getParkingLotId());
            getForPassVehicleRequestDto.setTenantId(parkingInfo.getTenantId());
            ObjectResultDto<ParkingLotResultDto> parkingLotForPassVehicle = platformParkingInfoService.getParkingLotByParkingId(getForPassVehicleRequestDto);
            if (parkingLotForPassVehicle.isFailed() || parkingLotForPassVehicle.getData() == null) {
                return resultDto.makeResult(PmsResultEnum.PARKING_LOT_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_LOT_NOT_FOUND.getComment());
            }
            ParkingLotResultDto parkingLotInfo = parkingLotForPassVehicle.getData();
            Map<Integer, String> pushTag = new HashMap<>();
            //获取tag
            //todo 根据排班获取tag
            //获取巡检员tag
            PushTagGetByParkingIdRequestDto pushTagInspectGet = new PushTagGetByParkingIdRequestDto();
            pushTagInspectGet.setParkingId(parkingInfo.getId());
            pushTagInspectGet.setTagTypes(TagTypeEnum.INSPECT_INFO_TAG.getValue());
            ListResultDto<PushTagResultDto> pushTagInspect = platformNotifyService.getPushTagByParkingId(pushTagInspectGet);
            if (pushTagInspect.isSuccess() && CollectionUtils.isNotEmpty(pushTagInspect.getItems())) {
                pushTag.put(TagTypeEnum.INSPECT_INFO_TAG.getValue(), pushTagInspect.getItems().get(0).getTag());
            } else {
                PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                pushTagAddRequestDto.setParkingId(parkingInfo.getId());
                pushTagAddRequestDto.setTag(TagPrefixConstant.INSPECT_INFO_TAG + parkingInfo.getId());
                pushTagAddRequestDto.setTagType(TagTypeEnum.INSPECT_INFO_TAG.getValue());
                platformNotifyService.savePushTag(pushTagAddRequestDto);
                pushTag.put(TagTypeEnum.INSPECT_INFO_TAG.getValue(), TagPrefixConstant.INSPECT_INFO_TAG + parkingInfo.getId());
            }
            //收费人员
            PushTagGetByParkingIdRequestDto pushTagCollect = new PushTagGetByParkingIdRequestDto();
            pushTagCollect.setParkingId(parkingInfo.getId());
            pushTagCollect.setTagTypes(TagTypeEnum.COLLECT_INFO_TAG.getValue());
            ListResultDto<PushTagResultDto> pushTagCollectResult =
                    platformNotifyService.getPushTagByParkingId(pushTagCollect);
            if (pushTagCollectResult.isSuccess() && CollectionUtils.isNotEmpty(pushTagCollectResult.getItems())) {
                pushTag.put(TagTypeEnum.COLLECT_INFO_TAG.getValue(), pushTagCollectResult.getItems().get(0).getTag());
            } else {
                PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                pushTagAddRequestDto.setParkingId(parkingInfo.getId());
                pushTagAddRequestDto.setTag(TagPrefixConstant.COLLECT_INFO_TAG + parkingInfo.getId());
                pushTagAddRequestDto.setTagType(TagTypeEnum.COLLECT_INFO_TAG.getValue());
                platformNotifyService.savePushTag(pushTagAddRequestDto);
                pushTag.put(TagTypeEnum.COLLECT_INFO_TAG.getValue(), TagPrefixConstant.COLLECT_INFO_TAG + parkingInfo.getId());
            }
            //停车场tag
            PushTagGetByParkingIdRequestDto pushTagGetByParkingIdRequestDto = new PushTagGetByParkingIdRequestDto();
            pushTagGetByParkingIdRequestDto.setParkingId(parkingInfo.getId());
            pushTagGetByParkingIdRequestDto.setTagTypes(TagTypeEnum.PARKING_INFO_TAG.getValue());
            ListResultDto<PushTagResultDto> pushRagByParkingId = platformNotifyService.getPushTagByParkingId(pushTagGetByParkingIdRequestDto);
            if (pushRagByParkingId.isSuccess() && CollectionUtils.isNotEmpty(pushRagByParkingId.getItems())) {
                pushTag.put(TagTypeEnum.PARKING_INFO_TAG.getValue(), pushRagByParkingId.getItems().get(0).getTag());
            } else {
                //生成pushTag
                PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                pushTagAddRequestDto.setParkingId(parkingInfo.getId());
                pushTagAddRequestDto.setTag(TagPrefixConstant.PARKING_INFO_TAG + parkingInfo.getId());
                pushTagAddRequestDto.setTagType(TagTypeEnum.PARKING_INFO_TAG.getValue());
                platformNotifyService.savePushTag(pushTagAddRequestDto);
                pushTag.put(TagTypeEnum.PARKING_INFO_TAG.getValue(), TagPrefixConstant.PARKING_INFO_TAG + parkingInfo.getId());
            }
            PushPassInspectNotifyRequestDto pushPassInspectNotifyRequestDto = new PushPassInspectNotifyRequestDto();
            pushPassInspectNotifyRequestDto.setTenantId(parkingInfo.getTenantId());
            PassingTypeEnum passingTypeEnum = PassingTypeEnum.parse(parkingRecordPayLoad.getPassingType());
            Map<Object, Object> params = new HashMap<>();
            params.put("dateTime", DateUtils.formatDate(parkingRecordPayLoad.getPassTime(),
                    DateUtils.YYYY_MM_DD_HH_MM_CHINESE));
            params.put("parkingName", parkingInfo.getFullName());
            params.put("parkingLotNumber", parkingLotInfo.getNumber());
            params.put("passingType", passingTypeEnum.getComment());
            pushPassInspectNotifyRequestDto.setParameters(params);
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(parkingRecordPayLoad));
            pushPassInspectNotifyRequestDto.setData(jsonObject);
            pushPassInspectNotifyRequestDto.setTags(pushTag);
            pushNotificationService.pushPassInspectNotification(pushPassInspectNotifyRequestDto);
            resultDto.success();
        } catch (Exception e) {
            log.error("过车记录推送巡检失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 获取用户的tag
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ObjectResultDto<PushTagGetInfoResultDto> getPushTag(PushTagGetInfoRequestDto requestDto) {
        ObjectResultDto<PushTagGetInfoResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            List<String> inspectTag = new ArrayList<>();
            List<String> collectTag = new ArrayList<>();
            List<Long> tagIds = new ArrayList<>();
            //巡检员
            ObjectResultDto<ParkingInfoResultDto> parkingInfo;
            ParkInspectorGetByUserIdRequestDto parkInspectorGetByUserIdRequestDto = new ParkInspectorGetByUserIdRequestDto();
            ObjectResultDto<ParkInspectorInfoResultDto> parkInspectorByUserId = platformInspectService.getParkInspectorByUserId(parkInspectorGetByUserIdRequestDto);
            if (parkInspectorByUserId.isSuccess() && parkInspectorByUserId.getData() != null) {
                ParkInspectorInfoResultDto parkInspectorInfoResultDto = parkInspectorByUserId.getData();

                //获取停车场
                ParkingInfoGetRequestDto parkingGetRequestDto = new ParkingInfoGetRequestDto();
                parkingGetRequestDto.setParkingId(parkInspectorInfoResultDto.getParkingId());
                parkingInfo = platformParkingInfoService.getParkInfoById(parkingGetRequestDto);
                //获取停车场tag
                PushTagGetByParkingIdRequestDto pushTagGetByParkingIdRequestDto = new PushTagGetByParkingIdRequestDto();
                pushTagGetByParkingIdRequestDto.setParkingId(parkInspectorInfoResultDto.getParkingId());
                pushTagGetByParkingIdRequestDto.setTagTypes(TagTypeEnum.PARKING_INFO_TAG.getValue());
                ListResultDto<PushTagResultDto> pushRagByParkingId = platformNotifyService.getPushTagByParkingId(pushTagGetByParkingIdRequestDto);
                if (pushRagByParkingId.isSuccess() && CollectionUtils.isNotEmpty(pushRagByParkingId.getItems())) {
                    inspectTag.add(pushRagByParkingId.getItems().get(0).getTag());
                } else {
                    //生成pushTag
                    PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                    pushTagAddRequestDto.setParkingId(parkInspectorInfoResultDto.getParkingId());
                    pushTagAddRequestDto.setTag(TagPrefixConstant.PARKING_INFO_TAG + parkInspectorInfoResultDto.getParkingId());
                    pushTagAddRequestDto.setTagType(TagTypeEnum.PARKING_INFO_TAG.getValue());
                    ObjectResultDto<PushTagSaveResultDto> pushTagSaveResultDtoObjectResultDto = platformNotifyService.savePushTag(pushTagAddRequestDto);
                    inspectTag.add(TagPrefixConstant.PARKING_INFO_TAG + parkInspectorInfoResultDto.getParkingId());
                    tagIds.add(pushTagSaveResultDtoObjectResultDto.getData().getId());
                }
                //获取巡检员tag
                PushTagGetByParkingIdRequestDto pushTagInspectGet = new PushTagGetByParkingIdRequestDto();
                pushTagInspectGet.setParkingId(parkInspectorInfoResultDto.getParkingId());
                pushTagInspectGet.setTagTypes(TagTypeEnum.INSPECT_INFO_TAG.getValue());
                ListResultDto<PushTagResultDto> pushTagInspect = platformNotifyService.getPushTagByParkingId(pushTagInspectGet);
                if (pushTagInspect.isSuccess() && CollectionUtils.isNotEmpty(pushTagInspect.getItems())) {
                    inspectTag.add(pushTagInspect.getItems().get(0).getTag());
                } else {
                    PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                    pushTagAddRequestDto.setParkingId(parkInspectorInfoResultDto.getParkingId());
                    pushTagAddRequestDto.setTag(TagPrefixConstant.INSPECT_INFO_TAG + parkInspectorInfoResultDto.getParkingId());
                    pushTagAddRequestDto.setTagType(TagTypeEnum.INSPECT_INFO_TAG.getValue());
                    ObjectResultDto<PushTagSaveResultDto> pushTagSaveResultDtoObjectResultDto = platformNotifyService.savePushTag(pushTagAddRequestDto);
                    inspectTag.add(TagPrefixConstant.INSPECT_INFO_TAG + parkInspectorInfoResultDto.getParkingId());
                    tagIds.add(pushTagSaveResultDtoObjectResultDto.getData().getId());
                }
                UserPushTagSaveRequestDto userPushTagSaveRequestDto = new UserPushTagSaveRequestDto();
                userPushTagSaveRequestDto.setTagId(tagIds);
                userPushTagSaveRequestDto.setUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
                userPushTagSaveRequestDto.setTenantId(parkingInfo.getData().getTenantId());
                platformNotifyService.saveUserPushTag(userPushTagSaveRequestDto);
            }
            //收费员
            ParkCollectorGetByUserIdRequestDto parkCollectorGetByUserIdRequestDto = new ParkCollectorGetByUserIdRequestDto();
            ObjectResultDto<ParkCollectorInfoResultDto> parkCollectorByUserId = platformInspectService.getParkCollectorByUserId(parkCollectorGetByUserIdRequestDto);
            if (parkCollectorByUserId.isSuccess() && parkCollectorByUserId.getData() != null) {
                ParkCollectorInfoResultDto parkCollectorByUserIdData = parkCollectorByUserId.getData();
                //获取停车场
                ParkingInfoGetRequestDto parkingGetRequestDto = new ParkingInfoGetRequestDto();
                parkingGetRequestDto.setParkingId(parkCollectorByUserIdData.getParkingId());
                parkingInfo = platformParkingInfoService.getParkInfoById(parkingGetRequestDto);
                //根据停车场获取tag
                PushTagGetByParkingIdRequestDto pushTagGetByParkingIdRequestDto = new PushTagGetByParkingIdRequestDto();
                pushTagGetByParkingIdRequestDto.setParkingId(parkCollectorByUserIdData.getParkingId());
                pushTagGetByParkingIdRequestDto.setTagTypes(TagTypeEnum.PARKING_INFO_TAG.getValue());
                ListResultDto<PushTagResultDto> pushRagByParkingId = platformNotifyService.getPushTagByParkingId(pushTagGetByParkingIdRequestDto);
                if (pushRagByParkingId.isSuccess() && CollectionUtils.isNotEmpty(pushRagByParkingId.getItems())) {
                    collectTag.add(pushRagByParkingId.getItems().get(0).getTag());
                } else {
                    //生成pushTag
                    PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                    pushTagAddRequestDto.setParkingId(parkCollectorByUserIdData.getParkingId());
                    pushTagAddRequestDto.setTag(TagPrefixConstant.PARKING_INFO_TAG + parkCollectorByUserIdData.getParkingId());
                    pushTagAddRequestDto.setTagType(TagTypeEnum.PARKING_INFO_TAG.getValue());
                    ObjectResultDto<PushTagSaveResultDto> pushTagSaveResultDtoObjectResultDto = platformNotifyService.savePushTag(pushTagAddRequestDto);
                    collectTag.add(TagPrefixConstant.PARKING_INFO_TAG + parkCollectorByUserIdData.getParkingId());
                    tagIds.add(pushTagSaveResultDtoObjectResultDto.getData().getId());
                }
                //收费员tag
                PushTagGetByParkingIdRequestDto pushTagInspectGet = new PushTagGetByParkingIdRequestDto();
                pushTagInspectGet.setParkingId(parkCollectorByUserIdData.getParkingId());
                pushTagInspectGet.setTagTypes(TagTypeEnum.COLLECT_INFO_TAG.getValue());
                ListResultDto<PushTagResultDto> pushTagInspect = platformNotifyService.getPushTagByParkingId(pushTagInspectGet);
                if (pushTagInspect.isSuccess() && CollectionUtils.isNotEmpty(pushTagInspect.getItems())) {
                    collectTag.add(pushTagInspect.getItems().get(0).getTag());
                } else {
                    PushTagAddRequestDto pushTagAddRequestDto = new PushTagAddRequestDto();
                    pushTagAddRequestDto.setParkingId(parkCollectorByUserIdData.getParkingId());
                    pushTagAddRequestDto.setTag(TagPrefixConstant.COLLECT_INFO_TAG + parkCollectorByUserIdData.getParkingId());
                    pushTagAddRequestDto.setTagType(TagTypeEnum.COLLECT_INFO_TAG.getValue());
                    ObjectResultDto<PushTagSaveResultDto> pushTagSaveResultDtoObjectResultDto = platformNotifyService.savePushTag(pushTagAddRequestDto);
                    collectTag.add(TagPrefixConstant.COLLECT_INFO_TAG + parkCollectorByUserIdData.getParkingId());
                    tagIds.add(pushTagSaveResultDtoObjectResultDto.getData().getId());
                }
                UserPushTagSaveRequestDto userPushTagSaveRequestDto = new UserPushTagSaveRequestDto();
                userPushTagSaveRequestDto.setTagId(tagIds);
                userPushTagSaveRequestDto.setUserId(FundamentalRequestContext.getFundamentalRequestContext().getSessionIdentity().getUserId());
                userPushTagSaveRequestDto.setTenantId(parkingInfo.getData().getTenantId());
                platformNotifyService.saveUserPushTag(userPushTagSaveRequestDto);
            }
            PushTagGetInfoResultDto pushTagGetInfoResultDto = new PushTagGetInfoResultDto();
            inspectTag.addAll(collectTag);
            List<String> distinctTag = inspectTag.stream().distinct().collect(Collectors.toList());
            pushTagGetInfoResultDto.setTags(distinctTag);
            objectResultDto.setData(pushTagGetInfoResultDto);
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取用户的tag失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 根据过车记录发送巡检消息
     *
     * @param passingRecordToInspectPayLoad PassingRecordToInspectPayLoad
     * @return ResultDto
     */
    @Override
    public ResultDto sendNotifyPassRecord(PassingRecordToInspectPayload passingRecordToInspectPayLoad) {
        ResultDto resultDto = new ResultDto();
        try {
            ParkingInfoGetRequestDto parkingInfoGetRequestDto = new ParkingInfoGetRequestDto();
            parkingInfoGetRequestDto.setParkingId(passingRecordToInspectPayLoad.getParkingId());
            ObjectResultDto<ParkingInfoResultDto> parkingInfoResultDtoObjectResultDto =
                    platformParkingInfoService.getParkInfoById(parkingInfoGetRequestDto);
            final ParkingInfoResultDto parkingInfoResultDto = parkingInfoResultDtoObjectResultDto.getData();
            if (parkingInfoResultDtoObjectResultDto.isFailed() || null == parkingInfoResultDto) {
                return resultDto.makeResult(PmsResultEnum.PARKING_NOT_FOUND.getValue(),
                        PmsResultEnum.PARKING_NOT_FOUND.getComment());
            }

            final ParkInspectorGetByParkingIdRequestDto parkInspectorGetByParkingIdRequestDto =
                    new ParkInspectorGetByParkingIdRequestDto();
            parkInspectorGetByParkingIdRequestDto.setParkingId(parkingInfoResultDto.getId());
            final ListResultDto<ParkInspectorInfoResultDto> inspector =
                    platformInspectService.getParkInspectorByParkingId(parkInspectorGetByParkingIdRequestDto);
            final ParkCollectorGetByParkingIdRequestDto parkCollectorGetByParkingIdRequestDto
                    = new ParkCollectorGetByParkingIdRequestDto();
            parkCollectorGetByParkingIdRequestDto.setParkingId(parkingInfoResultDto.getId());
            final ListResultDto<ParkCollectorInfoResultDto> collector =
                    platformInspectService.getParkCollectorByParkingId(parkCollectorGetByParkingIdRequestDto);

            Set<Long> userIdSet = new HashSet<>();
            final List<ParkInspectorInfoResultDto> inspectorItems = inspector.getItems();
            if (inspector.isSuccess() && inspectorItems != null) {
                for (ParkInspectorInfoResultDto dto : inspectorItems) {
                    userIdSet.add(dto.getUserId());
                }
            }
            final List<ParkCollectorInfoResultDto> collectorItems = collector.getItems();
            if (collector.isSuccess() && collectorItems != null) {
                for (ParkCollectorInfoResultDto dto : collectorItems) {
                    userIdSet.add(dto.getUserId());
                }
            }

            NotificationSendRequestDto requestDto = new NotificationSendRequestDto();
            requestDto.setTitle(DEFAULT_NOTIFICATION_MESSAGE_TITLE);

            requestDto.setNotifyType(NotificationTypeEnum.ALERT.getValue());
            JSONObject data = JSONObject.parseObject(JSONObject.toJSONString(passingRecordToInspectPayLoad));
            requestDto.setData(data);

            Map<Object, Object> parameters = new HashMap<>();
            parameters.put("dateTime", DateUtils.formatDateTime(passingRecordToInspectPayLoad.getPassTime()));

            parameters.put("parkingName", parkingInfoResultDto.getFullName());
            parameters.put("parkingLotNumber", passingRecordToInspectPayLoad.getParkingLotNumber());
            requestDto.setParameters(parameters);
            requestDto.setTenantId(parkingInfoResultDto.getTenantId());

            if (PassingTypeEnum.ENTRY.getValue().equals(passingRecordToInspectPayLoad.getPassingType())) {
                requestDto.setBizType(NotificationMessageTypeEnum.PASS_RECORD_INTO_INSPECT.getValue());
                setTemplate(requestDto, jpushProperty.getMessagePassRecordIntoInspect());
            } else {
                requestDto.setBizType(NotificationMessageTypeEnum.PASS_RECORD_OUT_INSPECT.getValue());
                setTemplate(requestDto, jpushProperty.getMessagePassRecordOutInspect());
            }
            for (Long userId : userIdSet) {
                requestDto.setUserId(userId);
                notificationService.addNotification(requestDto);
            }
        } catch (Exception e) {
            log.error("根据过车记录发送巡检消息失败：" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 设置消息模板
     *
     * @param requestDto      NotificationSendRequestDto
     * @param defaultTemplate 默认模板
     */
    private void setTemplate(NotificationSendRequestDto requestDto, String defaultTemplate) {
        TemplateQueryRequestDto templateQueryRequestDto = new TemplateQueryRequestDto();
        templateQueryRequestDto.setType(requestDto.getBizType());
        templateQueryRequestDto.setTenantId(requestDto.getTenantId());
        TemplateResultDto notificationTemplate = notificationService.getNotificationTemplateByType(templateQueryRequestDto);
        if (notificationTemplate != null) {
            requestDto.setTemplateId(notificationTemplate.getTemplateId());
        } else {
            requestDto.setTemplateId(defaultTemplate);
        }
    }
}
