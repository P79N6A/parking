package com.zoeeasy.cloud.integration.service.impl;

import com.alibaba.fastjson.JSON;
import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.scapegoat.infrastructure.message.RocketMessage;
import com.zoeeasy.cloud.core.cst.Const;
import com.zoeeasy.cloud.core.cst.MessageQueueDefinitions;
import com.zoeeasy.cloud.core.enums.PassingVehicleDataSourceEnum;
import com.zoeeasy.cloud.core.utils.ImageBase64Utils;
import com.zoeeasy.cloud.core.utils.UUIDTool;
import com.zoeeasy.cloud.integration.open.OpenPassRecordService;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddParkingImageRequest;
import com.zoeeasy.cloud.integration.open.dto.request.CloudAddPassRecordRequestDto;
import com.zoeeasy.cloud.integration.open.validator.CloudAddPassRecordRequestValidator;
import com.zoeeasy.cloud.message.MessageSendService;
import com.zoeeasy.cloud.message.payload.AnyPassRecordImagePayload;
import com.zoeeasy.cloud.message.payload.PassingVehiclePayload;
import com.zoeeasy.cloud.pms.garage.dto.result.CloudResultDto;
import com.zoeeasy.cloud.tool.oss.OssService;
import com.zoeeasy.cloud.tool.oss.dto.result.OssFileUploadResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
@Service("openPassRecordService")
@Slf4j
public class OpenPassRecordServiceImpl implements OpenPassRecordService {

    @Autowired
    private MessageSendService messageSendService;

    @Autowired
    private OssService ossService;

    /**
     * 停车管理系统添加过车记录发送至消息队列
     *
     * @param requestDto
     * @return
     */
    @Override
    public CloudResultDto clientAddPassRecord(@FluentValid({CloudAddPassRecordRequestValidator.class}) CloudAddPassRecordRequestDto requestDto) {
        //图片处理
        //图像上传后将url存入message
        String fullImageUrl = getImageUrl(requestDto.getFullImage());
        String plateImageUrl = null;
        if (StringUtils.isNotEmpty(requestDto.getPlateImage())) {
            plateImageUrl = getImageUrl(requestDto.getPlateImage());
        }
        return clientSimpleAddPassRecord(requestDto, fullImageUrl, plateImageUrl);
    }

    @Override
    public CloudResultDto clientSimpleAddPassRecord(CloudAddPassRecordRequestDto requestDto, String fullImage, String plateImage) throws ValidationException, BusinessException {
        CloudResultDto resultDto = new CloudResultDto();
        try {
            RocketMessage<PassingVehiclePayload> message = new RocketMessage<>();
            message.setDestination(MessageQueueDefinitions.Topic.PASSING_VEHICLE);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            message.setHashKey(requestDto.getPassingCode());
            PassingVehiclePayload passingVehiclePayload = new PassingVehiclePayload();
            passingVehiclePayload.setDataSource(PassingVehicleDataSourceEnum.ANY_PARKING.getValue());
            passingVehiclePayload.setPassingUuid(UUIDTool.getUUID());
            passingVehiclePayload.setThirdPassingId(requestDto.getPassingCode());
            passingVehiclePayload.setPlateNumber(requestDto.getPlateNumber());
            passingVehiclePayload.setPlateColor(requestDto.getPlateColor());
            passingVehiclePayload.setCarType(requestDto.getCarType());
            if (null != requestDto.getCarParkType()) {
                passingVehiclePayload.setParkingType(requestDto.getCarParkType());
            }
            passingVehiclePayload.setPassTime(DateTimeUtils.parseDate(requestDto.getPassTime(), Const.FORMAT_DATETIME));
            passingVehiclePayload.setDirect(requestDto.getDirection());
            passingVehiclePayload.setCloudParkingCode(requestDto.getCloudParkingCode());
            if (StringUtils.isNotEmpty(requestDto.getParkingLotCode())) {
                passingVehiclePayload.setCloudLotCode(requestDto.getParkingLotCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getParkingLotNumber())) {
                passingVehiclePayload.setCloudLotNumber(requestDto.getParkingLotNumber());
            }
            if (StringUtils.isNotEmpty(requestDto.getCloudGateCode())) {
                passingVehiclePayload.setCloudGateCode(requestDto.getCloudGateCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getCloudLaneCode())) {
                passingVehiclePayload.setCloudLaneCode(requestDto.getCloudLaneCode());
            }
            message.setPayload(passingVehiclePayload);
            messageSendService.sendAndSaveOrderlySync(message);
            log.info("------[停车管理系统添加过车记录发送至消息队列]--------[消息内容{}]------", JSON.toJSONString(message));
            //图片处理
            //图像上传后将url存入message
            String fullImageUrl = requestDto.getFullImage();
            String plateImageUrl = null;
            if (StringUtils.isNotEmpty(requestDto.getPlateImage())) {
                plateImageUrl = requestDto.getPlateImage();
            }
            //图片不同时为空时放入消息队列
            if (StringUtils.isNotEmpty(fullImageUrl) || StringUtils.isNotEmpty(plateImageUrl)) {
                RocketMessage<AnyPassRecordImagePayload> imageMessage = new RocketMessage<>();
                imageMessage.setDestination(MessageQueueDefinitions.Topic.ANY_PARKING_PASSING_RECORD_IMAGE);
                imageMessage.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                imageMessage.setMessageId(StringUtils.getUUID());
                imageMessage.setHashKey(requestDto.getPassingCode());
                AnyPassRecordImagePayload anyPassRecordImagePayload = new AnyPassRecordImagePayload();
                anyPassRecordImagePayload.setPassingCode(requestDto.getPassingCode());
                if (StringUtils.isNotEmpty(fullImageUrl)) {
                    anyPassRecordImagePayload.setFullImage(fullImageUrl);
                }
                anyPassRecordImagePayload.setFullImage(fullImageUrl);
                if (StringUtils.isNotEmpty(plateImageUrl)) {
                    anyPassRecordImagePayload.setPlateImage(plateImageUrl);
                }
                imageMessage.setPayload(anyPassRecordImagePayload);
                messageSendService.sendAndSaveOrderlySync(imageMessage);
                log.info("------[停车管理系统添加过车记录图片发送至消息队列]--------[消息内容{}]------", JSON.toJSONString(message));
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车管理系统添加过车记录发送至消息队列失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


    /**
     * 上传图片
     *
     * @param imageData
     * @return
     */
    private String getImageUrl(String imageData) {
        String imageUrl = null;
        if (StringUtils.isNotEmpty(imageData)) {
            String dataPrix = "";
            String data;
            String[] d = imageData.split("base64,");
            if (d != null && d.length == 2) {
                dataPrix = d[0];
                data = d[1];
            } else {
                data = imageData;
            }
            String fileName = UUIDTool.getUUID() + ImageBase64Utils.getFileNameSuffix(dataPrix);
            byte[] decode = ImageBase64Utils.base64String2ByteFun(data);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decode);
            ObjectResultDto<OssFileUploadResultDto> fileUploadResult = ossService.aliyunOssFileUpload(fileName, byteArrayInputStream);
            if (fileUploadResult.isSuccess() || fileUploadResult.getData() != null) {
                imageUrl = fileUploadResult.getData().getFileUrl();
            }
        }
        return imageUrl;
    }

    @Override
    public CloudResultDto addParkingImage(CloudAddParkingImageRequest requestDto) {
        CloudResultDto resultDto = new CloudResultDto();
        try {
            //图片处理 图像上传后将url存入message
            String fullImageUrl = requestDto.getFullImage();
            String plateImageUrl = null;
            if (StringUtils.isNotEmpty(requestDto.getPlateImage())) {
                plateImageUrl = requestDto.getPlateImage();
            }
            //图片不同时为空时放入消息队列
            if (StringUtils.isNotEmpty(fullImageUrl) || StringUtils.isNotEmpty(plateImageUrl)) {
                RocketMessage<AnyPassRecordImagePayload> imageMessage = new RocketMessage<>();
                imageMessage.setDestination(MessageQueueDefinitions.Topic.ANY_PARKING_RECORD_IMAGE);
                imageMessage.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                imageMessage.setMessageId(StringUtils.getUUID());
                imageMessage.setHashKey(requestDto.getPassingCode());
                AnyPassRecordImagePayload anyPassRecordImagePayload = new AnyPassRecordImagePayload();
                anyPassRecordImagePayload.setPassingCode(requestDto.getPassingCode());
                if (StringUtils.isNotEmpty(fullImageUrl)) {
                    anyPassRecordImagePayload.setFullImage(fullImageUrl);
                }
                anyPassRecordImagePayload.setFullImage(fullImageUrl);
                if (StringUtils.isNotEmpty(plateImageUrl)) {
                    anyPassRecordImagePayload.setPlateImage(plateImageUrl);
                }
                imageMessage.setPayload(anyPassRecordImagePayload);
                messageSendService.sendAndSaveOrderlySync(imageMessage);
                log.info("------[停车管理系统添加过车记录图片发送至消息队列]--------[消息内容{}]------", JSON.toJSONString(imageMessage));
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车管理系统添加过车记录发送至消息队列失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    @Override
    public CloudResultDto clientSimpleAddPassRecord(CloudAddPassRecordRequestDto requestDto, String fullImage, String plateImage, String destination, PassingVehicleDataSourceEnum dataSource) throws ValidationException, BusinessException {
        CloudResultDto resultDto = new CloudResultDto();
        try {
            RocketMessage<PassingVehiclePayload> message = new RocketMessage<>();
            message.setDestination(destination);
            message.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
            message.setMessageId(StringUtils.getUUID());
            message.setHashKey(requestDto.getPassingCode());
            PassingVehiclePayload passingVehiclePayload = new PassingVehiclePayload();
            passingVehiclePayload.setDataSource(dataSource.getValue());
            passingVehiclePayload.setPassingUuid(UUIDTool.getUUID());
            passingVehiclePayload.setThirdPassingId(requestDto.getPassingCode());
            passingVehiclePayload.setPlateNumber(requestDto.getPlateNumber());
            passingVehiclePayload.setPlateColor(requestDto.getPlateColor());
            passingVehiclePayload.setCarType(requestDto.getCarType());
            if (null != requestDto.getCarParkType()) {
                passingVehiclePayload.setParkingType(requestDto.getCarParkType());
            }
            passingVehiclePayload.setPassTime(DateTimeUtils.parseDate(requestDto.getPassTime(), Const.FORMAT_DATETIME));
            passingVehiclePayload.setDirect(requestDto.getDirection());
            passingVehiclePayload.setCloudParkingCode(requestDto.getCloudParkingCode());
            if (StringUtils.isNotEmpty(requestDto.getParkingLotCode())) {
                passingVehiclePayload.setCloudLotCode(requestDto.getParkingLotCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getParkingLotNumber())) {
                passingVehiclePayload.setCloudLotNumber(requestDto.getParkingLotNumber());
            }
            if (StringUtils.isNotEmpty(requestDto.getCloudGateCode())) {
                passingVehiclePayload.setCloudGateCode(requestDto.getCloudGateCode());
            }
            if (StringUtils.isNotEmpty(requestDto.getCloudLaneCode())) {
                passingVehiclePayload.setCloudLaneCode(requestDto.getCloudLaneCode());
            }
            message.setPayload(passingVehiclePayload);
            messageSendService.sendAndSaveOrderlySync(message);
            log.info("------[停车管理系统添加过车记录发送至消息队列]--------[消息内容{}]------", JSON.toJSONString(message));
            //图片处理
            //图像上传后将url存入message
            String fullImageUrl = requestDto.getFullImage();
            String plateImageUrl = null;
            if (StringUtils.isNotEmpty(requestDto.getPlateImage())) {
                plateImageUrl = requestDto.getPlateImage();
            }
            //图片不同时为空时放入消息队列
            if (StringUtils.isNotEmpty(fullImageUrl) || StringUtils.isNotEmpty(plateImageUrl)) {
                RocketMessage<AnyPassRecordImagePayload> imageMessage = new RocketMessage<>();
                imageMessage.setDestination(destination+"_"+"image");
                imageMessage.setSender(MessageQueueDefinitions.Sender.INTEGRATION);
                imageMessage.setMessageId(StringUtils.getUUID());
                imageMessage.setHashKey(requestDto.getPassingCode());
                AnyPassRecordImagePayload anyPassRecordImagePayload = new AnyPassRecordImagePayload();
                anyPassRecordImagePayload.setPassingCode(requestDto.getPassingCode());
                if (StringUtils.isNotEmpty(fullImageUrl)) {
                    anyPassRecordImagePayload.setFullImage(fullImageUrl);
                }
                anyPassRecordImagePayload.setFullImage(fullImageUrl);
                if (StringUtils.isNotEmpty(plateImageUrl)) {
                    anyPassRecordImagePayload.setPlateImage(plateImageUrl);
                }
                imageMessage.setPayload(anyPassRecordImagePayload);
                messageSendService.sendAndSaveOrderlySync(imageMessage);
                log.info("------[停车管理系统添加过车记录图片发送至消息队列]--------[消息内容{}]------", JSON.toJSONString(message));
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("停车管理系统添加过车记录发送至消息队列失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }


}
