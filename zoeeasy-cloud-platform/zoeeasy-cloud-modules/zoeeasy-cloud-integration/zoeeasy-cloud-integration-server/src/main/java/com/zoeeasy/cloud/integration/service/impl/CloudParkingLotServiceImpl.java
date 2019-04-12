package com.zoeeasy.cloud.integration.service.impl;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.enums.IntegrationResultEnum;
import com.zoeeasy.cloud.integration.park.CloudParkingLotService;
import com.zoeeasy.cloud.pms.image.ParkingImageService;
import com.zoeeasy.cloud.pms.image.dto.ParkingRecordImageRequestDto;
import com.zoeeasy.cloud.pms.park.ParkingLotInfoService;
import com.zoeeasy.cloud.pms.park.ParkingRecordService;
import com.zoeeasy.cloud.pms.park.ParkingVehicleRecordService;
import com.zoeeasy.cloud.pms.park.dto.request.CloudAddParkingLotRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ParkingLotResultDto;
import com.zoeeasy.cloud.pms.passing.PassingVehicleRecordService;
import com.zoeeasy.cloud.pms.passing.dto.request.PassVehicleRecordByPassingRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotInfoGetByCodeRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingRecordQueryByIntoRecordNoRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.request.PassingVehicleRecordGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.PassingVehicleRecordResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 云平台提供给管理系统调用泊位接口
 *
 * @Date: 2019/3/27
 * @author: lhj
 */
@Service(value = "cloudParkingLotService")
@Slf4j
public class CloudParkingLotServiceImpl implements CloudParkingLotService {

    @Autowired
    private ParkingLotInfoService parkingLotInfoService;

    @Autowired
    private ParkingImageService parkingImageService;

    @Autowired
    private PassingVehicleRecordService passingVehicleRecordService;

    @Autowired
    private ParkingRecordService parkingRecordService;

    @Autowired
    private ParkingVehicleRecordService parkingVehicleRecordService;

    /**
     * 添加泊位数据
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto clientAddParkingLot(CloudAddParkingLotRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            //查询泊位是否存在
            ParkingLotInfoGetByCodeRequestDto parkingLotInfoGetByCodeRequestDto = new ParkingLotInfoGetByCodeRequestDto();
            parkingLotInfoGetByCodeRequestDto.setParkingLotCode(requestDto.getParkingLotCode());
            ObjectResultDto<ParkingLotResultDto> parkingLotByCode = parkingLotInfoService.getParkingLotByLotCode(parkingLotInfoGetByCodeRequestDto);
            if (null == parkingLotByCode.getData()) {
                return resultDto.makeResult(IntegrationResultEnum.PARKING_LOT_NOT_EMPTY.getValue(), IntegrationResultEnum.PARKING_LOT_NOT_EMPTY.getComment());
            }
            ParkingRecordQueryByIntoRecordNoRequestDto parkingRecordQueryByIntoRecordNoRequestDto = new ParkingRecordQueryByIntoRecordNoRequestDto();
            //平台停车记录泊位根据intoRecordNo维护
            ParkingLotResultDto parkingLotByCodeData = parkingLotByCode.getData();
            parkingRecordQueryByIntoRecordNoRequestDto.setIntoRecordNo(requestDto.getPassingCode());
            parkingRecordQueryByIntoRecordNoRequestDto.setParkingLotId(parkingLotByCodeData.getId());
            parkingRecordQueryByIntoRecordNoRequestDto.setParkingLotCode(parkingLotByCodeData.getCode());
            parkingRecordQueryByIntoRecordNoRequestDto.setParkingLotNumber(parkingLotByCodeData.getNumber());
            ResultDto updateParkingRecordByIntoRecordNo = parkingRecordService.updateParkingRecordByIntoRecordNo(parkingRecordQueryByIntoRecordNoRequestDto);
            if (updateParkingRecordByIntoRecordNo.isFailed()) {
                return resultDto.makeResult(IntegrationResultEnum.UPDATE_PARKING_RECORD_IS_FAILED.getValue(), IntegrationResultEnum.UPDATE_PARKING_RECORD_IS_FAILED.getComment());
            }
            //在停车辆根据intoRecordNo维护
            ResultDto updateParkingVehicleRecord = parkingVehicleRecordService.updateParkingVehicleRecord(parkingRecordQueryByIntoRecordNoRequestDto);
            if (updateParkingVehicleRecord.isFailed()) {
                return resultDto.makeResult(IntegrationResultEnum.UPDATE_PARKING_VEHICLE_RECORD.getValue(), IntegrationResultEnum.UPDATE_PARKING_VEHICLE_RECORD.getComment());
            }
            //过车记录根据passNo维护
            PassVehicleRecordByPassingRequestDto passVehicleRecordByPassingRequestDto = new PassVehicleRecordByPassingRequestDto();
            passVehicleRecordByPassingRequestDto.setParkingLotId(parkingLotByCodeData.getId());
            passVehicleRecordByPassingRequestDto.setParkingLotCode(parkingLotByCodeData.getCode());
            passVehicleRecordByPassingRequestDto.setParkingLotNumber(parkingLotByCodeData.getNumber());
            passVehicleRecordByPassingRequestDto.setPassingNo(requestDto.getPassingCode());
            ResultDto updatePassVehicleRecordByCode = passingVehicleRecordService.updatePassVehicleRecordByCode(passVehicleRecordByPassingRequestDto);
            if (updatePassVehicleRecordByCode.isFailed()) {
                return resultDto.makeResult(IntegrationResultEnum.PASSING_VEHICLE_RECORD_NOT_EMPTY.getValue(), IntegrationResultEnum.PASSING_VEHICLE_RECORD_NOT_EMPTY.getComment());
            }
            //添加停车图片，图片表根据bizNo维护
            ParkingRecordImageRequestDto parkingRecordImageRequestDto = new ParkingRecordImageRequestDto();
            //查询过车记录
            PassingVehicleRecordGetRequestDto passingVehicleRecordGetRequestDto = new PassingVehicleRecordGetRequestDto();
            passingVehicleRecordGetRequestDto.setPassingNo(requestDto.getPassingCode());
            ObjectResultDto<PassingVehicleRecordResultDto> passVehicleByPassNo = passingVehicleRecordService.getPassVehicleByPassNo(passingVehicleRecordGetRequestDto);
            if (null != passVehicleByPassNo.getData()) {
                parkingRecordImageRequestDto.setBizId(passVehicleByPassNo.getData().getId());
                parkingRecordImageRequestDto.setTenantId(passVehicleByPassNo.getData().getTenantId());
            }
            parkingRecordImageRequestDto.setBizNo(requestDto.getPassingCode());
            parkingRecordImageRequestDto.setFullImage(requestDto.getFullImage());
            parkingRecordImageRequestDto.setPlateImage(requestDto.getPlateImage());
            parkingRecordImageRequestDto.setParkingCode(requestDto.getCloudParkingCode());
            ResultDto addParkingRecordImage = parkingImageService.addParkingRecordImage(parkingRecordImageRequestDto);
            if (addParkingRecordImage.isFailed()) {
                return resultDto.makeResult(IntegrationResultEnum.PARKING_RECORD_IMAGE_FAILED.getValue(), IntegrationResultEnum.PARKING_RECORD_IMAGE_FAILED.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("添加泊位数据失败" + e.getMessage(), e);
            resultDto.failed();
        }
        return resultDto;
    }
}
