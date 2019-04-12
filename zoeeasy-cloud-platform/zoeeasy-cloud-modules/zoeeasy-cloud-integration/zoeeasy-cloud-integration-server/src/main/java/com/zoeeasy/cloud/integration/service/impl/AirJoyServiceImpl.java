package com.zoeeasy.cloud.integration.service.impl;

import cn.hutool.core.date.DateUtil;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.DeviceProviderEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.gather.enums.AirJoyDetectorStatusEnum;
import com.zoeeasy.cloud.gather.enums.DetectorChangeTypeEnum;
import com.zoeeasy.cloud.gather.enums.GatherResultEnum;
import com.zoeeasy.cloud.gather.enums.AirJoyReportDataTypeEnum;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.airjoy.AirJoyPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticHeartBeatAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticReportRecordAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticStatusRecordAddRequestDto;
import com.zoeeasy.cloud.integration.magnetic.AirJoyService;
import com.zoeeasy.cloud.integration.message.MessageSendIntegrationService;
import com.zoeeasy.cloud.integration.message.dto.request.MagneticPassMessageRequestDto;
import com.zoeeasy.cloud.pds.device.PlatformDeviceService;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorEnum;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorGetByMacRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorUpdateLastHeartbeatTimeRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.airjoy.MagneticDetectorGetByMacResultDto;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 艾佳相关服务
 *
 * @author zwq
 */
@Service(value = "airJoyService")
@Slf4j
public class AirJoyServiceImpl implements AirJoyService {

    @Autowired
    private PlatformDeviceService platformDeviceService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    /**
     * 车位状态变更推送
     *
     * @param requestDto AirJoyPushRequestDto
     * @return ResultDto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto airJoyHeartChangePush(AirJoyPushRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticDetectorGetByMacRequestDto magneticDetectorGetByMacRequestDto =
                    new MagneticDetectorGetByMacRequestDto();
            magneticDetectorGetByMacRequestDto.setProvider(DeviceProviderEnum.AIRJOY.getValue());
            magneticDetectorGetByMacRequestDto.setSerialNumber(requestDto.getMac());
            ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = platformDeviceService.
                    getMagneticDetectorByMac(magneticDetectorGetByMacRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                log.error("艾佳地磁检测设备查询失败:错误的设备编号" + requestDto.getMac());
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            if (objectResultDto.getData().getParkingLotId() == 0) {
                return resultDto.makeResult(MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getValue(),
                        MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getComment());
            }
            MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = objectResultDto.getData();
            //泊位状态变更类型
            Integer changeType = null;
            //过车类型
            Integer passingType = PassingTypeEnum.UN_KNOW.getValue();
            if (AirJoyDetectorStatusEnum.OUT.getValue().equals(requestDto.getStatus())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEOUT.getValue();
                passingType = PassingTypeEnum.EXIT.getValue();
            } else if (AirJoyDetectorStatusEnum.ON.getValue().equals(requestDto.getStatus())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEIN.getValue();
                passingType = PassingTypeEnum.ENTRY.getValue();
            }
            //业务数据
            if (requestDto.getDataType().equals(AirJoyReportDataTypeEnum.BUSINESS.getValue())) {
                MagneticStatusRecordAddRequestDto magneticStatusRecordAddRequestDto = new MagneticStatusRecordAddRequestDto();
                magneticStatusRecordAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
                magneticStatusRecordAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticStatusRecordAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticStatusRecordAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
                magneticStatusRecordAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
                magneticStatusRecordAddRequestDto.setSerialNumber(requestDto.getMac());
                magneticStatusRecordAddRequestDto.setChangeTime(DateUtil.parse(requestDto.getUploadTime()));
                if (changeType != null) {
                    magneticStatusRecordAddRequestDto.setChangeType(changeType);
                } else {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getValue(),
                            GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getComment());
                }
                magneticStatusRecordAddRequestDto.setOccupyStatus(Integer.parseInt(requestDto.getStatus()));
                magneticStatusRecordAddRequestDto.setCreationTime(new Date());
                ResultDto insertResult = magneticHeartBeatService.addMagneticStatusRecord(magneticStatusRecordAddRequestDto);
                if (insertResult.isSuccess()) {
                    MagneticReportRecordAddRequestDto magneticReportRecordAddRequestDto =
                            modelMapper.map(magneticStatusRecordAddRequestDto, MagneticReportRecordAddRequestDto.class);
                    ResultDto result = magneticHeartBeatService.magneticReportRecordAdd(magneticReportRecordAddRequestDto);
                    if (result.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getComment());
                    }
                    MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto =
                            new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                    updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                    updateRequestDto.setLastHeartbeatTime(magneticStatusRecordAddRequestDto.getChangeTime());
                    updateRequestDto.setOccupyStatus(Integer.parseInt(requestDto.getStatus()));
                    resultDto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                    if (resultDto.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
                    }
                    MagneticPassMessageRequestDto magneticPassMessageRequestDto = new MagneticPassMessageRequestDto();
                    magneticPassMessageRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                    magneticPassMessageRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                    magneticPassMessageRequestDto.setPassTime(DateUtil.parse(requestDto.getUploadTime()));
                    magneticPassMessageRequestDto.setPassingType(passingType);
                    resultDto = messageSendIntegrationService.sendMagneticMessage(magneticPassMessageRequestDto);
                    if (resultDto.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MESSAGE_PUSH_ERR.getValue(),
                                GatherResultEnum.MESSAGE_PUSH_ERR.getComment());
                    }
                }
            } else if (requestDto.getDataType().equals(AirJoyReportDataTypeEnum.HEARTBEAT.getValue())) {
                //插入心跳数据
                MagneticHeartBeatAddRequestDto magneticHeartBeatAddRequestDto = new MagneticHeartBeatAddRequestDto();
                magneticHeartBeatAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
                magneticHeartBeatAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticHeartBeatAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticHeartBeatAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
                magneticHeartBeatAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
                magneticHeartBeatAddRequestDto.setSerialNumber(requestDto.getMac());
                magneticHeartBeatAddRequestDto.setOccupyStatus(Integer.parseInt(requestDto.getStatus()));
                magneticHeartBeatAddRequestDto.setBeatTime(DateUtil.parse(requestDto.getUploadTime()));
                magneticHeartBeatAddRequestDto.setCreationTime(new Date());
                ResultDto insertResult = magneticHeartBeatService.addMagneticHeartBeat(magneticHeartBeatAddRequestDto);
                //判断心跳变更后，占用状态是否变更
                if (insertResult.isSuccess()) {
                    MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto =
                            new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                    updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                    updateRequestDto.setLastHeartbeatTime(magneticHeartBeatAddRequestDto.getBeatTime());
                    updateRequestDto.setOccupyStatus(Integer.parseInt(requestDto.getStatus()));
                    resultDto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                    if (resultDto.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
                    }
                    if (!requestDto.getStatus().equals(String.valueOf(objectResultDto.getData().getOccupyStatus()))) {
                        MagneticReportRecordAddRequestDto magneticReportRecordAddRequestDto =
                                modelMapper.map(magneticHeartBeatAddRequestDto, MagneticReportRecordAddRequestDto.class);
                        magneticReportRecordAddRequestDto.setChangeTime(magneticHeartBeatAddRequestDto.getBeatTime());
                        if (changeType != null) {
                            magneticReportRecordAddRequestDto.setChangeType(changeType);
                        } else {
                            return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getValue(),
                                    GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getComment());
                        }
                        resultDto = magneticHeartBeatService.magneticReportRecordAdd(magneticReportRecordAddRequestDto);
                        if (resultDto.isFailed()) {
                            return resultDto.makeResult(GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getValue(),
                                    GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getComment());
                        }
                        MagneticPassMessageRequestDto magneticPassMessageRequestDto = new MagneticPassMessageRequestDto();
                        magneticPassMessageRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                        magneticPassMessageRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                        magneticPassMessageRequestDto.setPassTime(DateUtil.parse(requestDto.getUploadTime()));
                        magneticPassMessageRequestDto.setPassingType(passingType);
                        resultDto = messageSendIntegrationService.sendMagneticMessage(magneticPassMessageRequestDto);
                        if (resultDto.isFailed()) {
                            return resultDto.makeResult(GatherResultEnum.MESSAGE_PUSH_ERR.getValue(),
                                    GatherResultEnum.MESSAGE_PUSH_ERR.getComment());
                        }
                    }
                } else {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getValue(),
                            GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getComment());
                }
            } else {
                return resultDto.makeResult(GatherResultEnum.DATATYPE_ERR.getValue(),
                        GatherResultEnum.DATATYPE_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("艾佳地磁检测数据推送失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
