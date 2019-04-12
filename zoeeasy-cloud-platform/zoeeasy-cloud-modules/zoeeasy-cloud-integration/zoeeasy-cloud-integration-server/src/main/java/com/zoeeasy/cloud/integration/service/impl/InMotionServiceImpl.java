package com.zoeeasy.cloud.integration.service.impl;

import cn.hutool.core.date.DateUtil;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.DeviceProviderEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.gather.enums.AirJoyDetectorStatusEnum;
import com.zoeeasy.cloud.gather.enums.DetectorChangeTypeEnum;
import com.zoeeasy.cloud.gather.enums.GatherResultEnum;
import com.zoeeasy.cloud.gather.enums.InMotionCmdEnum;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion.InMotionHeartBeatPushRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.inmotion.InMotionHeartbeatBodyRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticHeartBeatAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticReportRecordAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticStatusRecordAddRequestDto;
import com.zoeeasy.cloud.integration.magnetic.InMotionService;
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
 * 易姆讯相关服务
 *
 * @author zwq
 */
@Service(value = "inMotionService")
@Slf4j
public class InMotionServiceImpl implements InMotionService {

    @Autowired
    private PlatformDeviceService platformDeviceService;

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 地磁检测器心跳处理
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto inMotionHeartBeat(InMotionHeartBeatPushRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            InMotionHeartbeatBodyRequestDto bodyRequestDto = requestDto.getBody();
            MagneticDetectorGetByMacRequestDto magneticDetectorGetByMacRequestDto = new MagneticDetectorGetByMacRequestDto();
            magneticDetectorGetByMacRequestDto.setProvider(DeviceProviderEnum.INMOTION.getValue());
            magneticDetectorGetByMacRequestDto.setSerialNumber(bodyRequestDto.getDeviceID());
            ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = platformDeviceService.getMagneticDetectorByMac(magneticDetectorGetByMacRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                log.error("易姆讯地磁检测设备查询失败:错误的设备编号" + bodyRequestDto.getDeviceID());
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            if (objectResultDto.getData().getParkingLotId() == 0) {
                return resultDto.makeResult(MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getValue(), MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getComment());
            }
            MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = objectResultDto.getData();
            Integer changeType = null;
            Integer passingType = PassingTypeEnum.UN_KNOW.getValue();
            if (AirJoyDetectorStatusEnum.OUT.getValue().equals(bodyRequestDto.getParkingStatu().toString())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEOUT.getValue();
                passingType = PassingTypeEnum.EXIT.getValue();
            } else if (AirJoyDetectorStatusEnum.ON.getValue().equals(bodyRequestDto.getParkingStatu().toString())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEIN.getValue();
                passingType = PassingTypeEnum.ENTRY.getValue();
            }
            //心跳处理
            if (requestDto.getCmd().equals(InMotionCmdEnum.SENDDEVICEHEARTBEAT.getComment())) {
                MagneticHeartBeatAddRequestDto magneticHeartBeatAddRequestDto = new MagneticHeartBeatAddRequestDto();
                magneticHeartBeatAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
                magneticHeartBeatAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticHeartBeatAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticHeartBeatAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
                magneticHeartBeatAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
                magneticHeartBeatAddRequestDto.setSerialNumber(bodyRequestDto.getDeviceID());
                magneticHeartBeatAddRequestDto.setOccupyStatus(bodyRequestDto.getParkingStatu());
                magneticHeartBeatAddRequestDto.setBeatTime(DateUtil.parse(bodyRequestDto.getTime()));
                magneticHeartBeatAddRequestDto.setBattery(bodyRequestDto.getBattary());
                magneticHeartBeatAddRequestDto.setCommandCode(requestDto.getCmd());
                magneticHeartBeatAddRequestDto.setErrorCode(bodyRequestDto.getErrcode());
                magneticHeartBeatAddRequestDto.setCreationTime(new Date());
                ResultDto insertResult = magneticHeartBeatService.addMagneticHeartBeat(magneticHeartBeatAddRequestDto);
                if (insertResult.isSuccess()) {
                    MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                    updateRequestDto.setLastHeartbeatTime(magneticHeartBeatAddRequestDto.getBeatTime());
                    updateRequestDto.setOccupyStatus(bodyRequestDto.getParkingStatu());
                    updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                    updateRequestDto.setElectricity(bodyRequestDto.getBattary());
                    resultDto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                    if (resultDto.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
                    }
                    if (!bodyRequestDto.getParkingStatu().equals(objectResultDto.getData().getOccupyStatus())) {
                        MagneticReportRecordAddRequestDto magneticReportRecordAddRequestDto = modelMapper.map(magneticHeartBeatAddRequestDto, MagneticReportRecordAddRequestDto.class);
                        magneticReportRecordAddRequestDto.setChangeTime(magneticHeartBeatAddRequestDto.getBeatTime());
                        if (changeType != null) {
                            magneticReportRecordAddRequestDto.setChangeType(changeType);
                        } else {
                            return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getValue(),
                                    GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getComment());
                        }
                        ResultDto result = magneticHeartBeatService.magneticReportRecordAdd(magneticReportRecordAddRequestDto);
                        if (result.isFailed()) {
                            return resultDto.makeResult(GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getValue(),
                                    GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getComment());
                        }
                    }
                } else {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getValue(),
                            GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getComment());
                }
                MagneticPassMessageRequestDto magneticPassMessageRequestDto = new MagneticPassMessageRequestDto();
                magneticPassMessageRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticPassMessageRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticPassMessageRequestDto.setPassTime(DateUtil.parse(bodyRequestDto.getTime()));
                if (passingType != null) {
                    magneticPassMessageRequestDto.setPassingType(passingType);
                } else {
                    magneticPassMessageRequestDto.setPassingType(PassingTypeEnum.UN_KNOW.getValue());
                }
                resultDto = messageSendIntegrationService.sendMagneticMessage(magneticPassMessageRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto.makeResult(GatherResultEnum.MESSAGE_PUSH_ERR.getValue(),
                            GatherResultEnum.MESSAGE_PUSH_ERR.getComment());
                }
            }
            //推送状态
            if (requestDto.getCmd().equals(InMotionCmdEnum.SENDPARKSTATU.getComment())) {
                //业务数据
                MagneticStatusRecordAddRequestDto magneticStatusRecordAddRequestDto = new MagneticStatusRecordAddRequestDto();
                magneticStatusRecordAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
                magneticStatusRecordAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticStatusRecordAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticStatusRecordAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
                magneticStatusRecordAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
                magneticStatusRecordAddRequestDto.setSerialNumber(bodyRequestDto.getDeviceID());
                magneticStatusRecordAddRequestDto.setChangeTime(DateUtil.parse(bodyRequestDto.getTime()));
                magneticStatusRecordAddRequestDto.setPassTime(bodyRequestDto.getPassTime());
                magneticStatusRecordAddRequestDto.setSequence(Long.valueOf(bodyRequestDto.getSequence()));
                magneticStatusRecordAddRequestDto.setBattery(bodyRequestDto.getBattary());
                magneticStatusRecordAddRequestDto.setRssi(bodyRequestDto.getRssi());
                magneticStatusRecordAddRequestDto.setCreationTime(new Date());
                if (changeType != null) {
                    magneticStatusRecordAddRequestDto.setChangeType(changeType);
                } else {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getValue(),
                            GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getComment());
                }
                magneticStatusRecordAddRequestDto.setOccupyStatus(bodyRequestDto.getParkingStatu());
                ResultDto insertResult = magneticHeartBeatService.addMagneticStatusRecord(magneticStatusRecordAddRequestDto);
                if (insertResult.isSuccess()) {
                    MagneticReportRecordAddRequestDto magneticReportRecordAddRequestDto = modelMapper.map(magneticStatusRecordAddRequestDto, MagneticReportRecordAddRequestDto.class);
                    ResultDto result = magneticHeartBeatService.magneticReportRecordAdd(magneticReportRecordAddRequestDto);
                    if (result.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getComment());
                    }
                    MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                    updateRequestDto.setLastHeartbeatTime(magneticStatusRecordAddRequestDto.getChangeTime());
                    updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                    updateRequestDto.setOccupyStatus(bodyRequestDto.getParkingStatu());
                    updateRequestDto.setElectricity(bodyRequestDto.getBattary());
                    resultDto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                    if (resultDto.isFailed()) {
                        return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                                GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
                    }
                } else {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_STATUS_RECORD_INSERT_ERR.getValue(),
                            GatherResultEnum.MAGNETIC_STATUS_RECORD_INSERT_ERR.getComment());
                }
                MagneticPassMessageRequestDto magneticPassMessageRequestDto = new MagneticPassMessageRequestDto();
                magneticPassMessageRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
                magneticPassMessageRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
                magneticPassMessageRequestDto.setPassTime(DateUtil.parse(bodyRequestDto.getTime()));
                if (passingType != null) {
                    magneticPassMessageRequestDto.setPassingType(passingType);
                } else {
                    magneticPassMessageRequestDto.setPassingType(PassingTypeEnum.UN_KNOW.getValue());
                }
                resultDto = messageSendIntegrationService.sendMagneticMessage(magneticPassMessageRequestDto);
                if (resultDto.isFailed()) {
                    return resultDto.makeResult(GatherResultEnum.MESSAGE_PUSH_ERR.getValue(),
                            GatherResultEnum.MESSAGE_PUSH_ERR.getComment());
                }
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("易姆讯地磁心跳处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
