package com.zoeeasy.cloud.integration.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.core.enums.DeviceProviderEnum;
import com.zoeeasy.cloud.core.enums.PassingTypeEnum;
import com.zoeeasy.cloud.gather.enums.DetectorChangeTypeEnum;
import com.zoeeasy.cloud.gather.enums.GatherResultEnum;
import com.zoeeasy.cloud.gather.magnetic.MagneticHeartBeatService;
import com.zoeeasy.cloud.gather.magnetic.dto.request.fushang.*;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticHeartBeatAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticReportRecordAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.request.magneticdetector.MagneticStatusRecordAddRequestDto;
import com.zoeeasy.cloud.gather.magnetic.dto.result.SendResultSto;
import com.zoeeasy.cloud.integration.enums.FuShangResultEnum;
import com.zoeeasy.cloud.integration.magnetic.FuShangService;
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
 * @Date: 2018/12/5
 * @author: lhj
 */
@Service(value = "fuShangService")
@Slf4j
public class FuShangServiceImpl implements FuShangService {

    @Autowired
    private PlatformDeviceService platformDeviceService;

    @Autowired
    private MagneticHeartBeatService magneticHeartBeatService;

    @Autowired
    private MessageSendIntegrationService messageSendIntegrationService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 车位状态处理
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SendResultSto fuShangParkStatusPush(FuShangParkStatusPushRequestDto requestDto) {
        SendResultSto resultDto = new SendResultSto();
        try {
            FuShangParkStatusBodyRequestDto parkStatusBodyRequestDto = requestDto.getBody();
            //校验和
            StringBuilder sb = new StringBuilder();
            sb.append(requestDto.getCmd());
            sb.append(parkStatusBodyRequestDto.getParkID());
            sb.append(parkStatusBodyRequestDto.getTime());
            sb.append(parkStatusBodyRequestDto.getDeviceID());
            sb.append(parkStatusBodyRequestDto.getRssi());
            sb.append(parkStatusBodyRequestDto.getPassTime());
            sb.append(parkStatusBodyRequestDto.getSequence());
            sb.append(parkStatusBodyRequestDto.getBattary());
            sb.append(parkStatusBodyRequestDto.getParkingStatu());
            String md5Str = DigestUtil.md5Hex(sb.toString().toUpperCase()).toUpperCase();
            if (!(parkStatusBodyRequestDto.getToken().equals(md5Str))) {
                return resultDto.makeResult(GatherResultEnum.TOKEN_ERR.getValue(),
                        GatherResultEnum.TOKEN_ERR.getComment());
            }
            //地磁检测器是否存在
            MagneticDetectorGetByMacRequestDto magneticDetectorGetByMacRequestDto =
                    new MagneticDetectorGetByMacRequestDto();
            magneticDetectorGetByMacRequestDto.setProvider(DeviceProviderEnum.FUSHANG.getValue());
            magneticDetectorGetByMacRequestDto.setSerialNumber(parkStatusBodyRequestDto.getDeviceID());
            ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = platformDeviceService.
                    getMagneticDetectorByMac(magneticDetectorGetByMacRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                log.error("富尚地磁检测设备查询失败:错误的设备编号" + parkStatusBodyRequestDto.getDeviceID());
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            //是否关联泊位
            if (objectResultDto.getData().getParkingLotId() == 0) {
                return resultDto.makeResult(MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getValue(), MagneticDetectorEnum.
                        NO_CONNECTION_PARKING_LOT.getComment());
            }
            //是否注册
            if (!objectResultDto.getData().getRegistered()) {
                return resultDto.makeResult(MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_REGISTERED.getValue(), MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_REGISTERED.getComment());
            }
            MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = objectResultDto.getData();
            Integer changeType = null;
            Integer passingType = PassingTypeEnum.UN_KNOW.getValue();
            if (FuShangResultEnum.LEISURE.getValue().equals(parkStatusBodyRequestDto.getParkingStatu())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEOUT.getValue();
                passingType = PassingTypeEnum.EXIT.getValue();
            } else if (FuShangResultEnum.OCCUPATION.getValue().equals(parkStatusBodyRequestDto.getParkingStatu())) {
                changeType = DetectorChangeTypeEnum.CAR_COMEIN.getValue();
                passingType = PassingTypeEnum.ENTRY.getValue();
            }
            //业务数据
            MagneticStatusRecordAddRequestDto magneticStatusRecordAddRequestDto = new MagneticStatusRecordAddRequestDto();
            magneticStatusRecordAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
            magneticStatusRecordAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
            magneticStatusRecordAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
            magneticStatusRecordAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
            magneticStatusRecordAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
            magneticStatusRecordAddRequestDto.setSerialNumber(parkStatusBodyRequestDto.getDeviceID());
            magneticStatusRecordAddRequestDto.setChangeTime(DateUtil.parse(parkStatusBodyRequestDto.getTime()));
            magneticStatusRecordAddRequestDto.setPassTime(parkStatusBodyRequestDto.getPassTime());
            magneticStatusRecordAddRequestDto.setSequence(Long.valueOf(parkStatusBodyRequestDto.getSequence()));
            //电量状态
            magneticStatusRecordAddRequestDto.setBattery(parkStatusBodyRequestDto.getBattary());
            magneticStatusRecordAddRequestDto.setRssi(parkStatusBodyRequestDto.getRssi());
            magneticStatusRecordAddRequestDto.setCreationTime(new Date());
            if (changeType != null) {
                magneticStatusRecordAddRequestDto.setChangeType(changeType);
            } else {
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_STATUS.getComment());
            }
            magneticStatusRecordAddRequestDto.setOccupyStatus(parkStatusBodyRequestDto.getParkingStatu());
            //地磁检测器状态变更记录添加
            ResultDto insertResult = magneticHeartBeatService.addMagneticStatusRecord(magneticStatusRecordAddRequestDto);
            if (insertResult.isSuccess()) {
                MagneticReportRecordAddRequestDto magneticReportRecordAddRequestDto = modelMapper.map(magneticStatusRecordAddRequestDto, MagneticReportRecordAddRequestDto.class);
                //地磁检测器记录添加
                ResultDto result = magneticHeartBeatService.magneticReportRecordAdd(magneticReportRecordAddRequestDto);
                if (result.isFailed()) {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getValue(),
                            GatherResultEnum.MAGNETIC_REPORT_RECORD_INSERT_ERR.getComment());
                }
                MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                updateRequestDto.setLastHeartbeatTime(magneticStatusRecordAddRequestDto.getChangeTime());
                updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                updateRequestDto.setOccupyStatus(parkStatusBodyRequestDto.getParkingStatu());
                updateRequestDto.setElectricity(parkStatusBodyRequestDto.getBattary());
                //维护最后心跳时间
                ResultDto dto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                if (dto.isFailed()) {
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
            magneticPassMessageRequestDto.setPassTime(DateUtil.parse(parkStatusBodyRequestDto.getTime()));
            if (passingType != null) {
                magneticPassMessageRequestDto.setPassingType(passingType);
            } else {
                magneticPassMessageRequestDto.setPassingType(PassingTypeEnum.UN_KNOW.getValue());
            }
            //推送
            ResultDto dto = messageSendIntegrationService.sendMagneticMessage(magneticPassMessageRequestDto);
            if (dto.isFailed()) {
                return resultDto.makeResult(GatherResultEnum.MESSAGE_PUSH_ERR.getValue(),
                        GatherResultEnum.MESSAGE_PUSH_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("富尚车位状态处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 心跳数据处理
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SendResultSto fuShangHeartBeatPush(FuShangHeartBeatPushRequestDto requestDto) {
        SendResultSto resultDto = new SendResultSto();
        try {
            FuShangHeartBeatBodyRequestDto heartBeatBodyRequestDto = requestDto.getBody();
            //校验和
            StringBuilder sb = new StringBuilder();
            sb.append(requestDto.getCmd());
            sb.append(heartBeatBodyRequestDto.getParkID());
            sb.append(heartBeatBodyRequestDto.getTime());
            sb.append(heartBeatBodyRequestDto.getDeviceID());
            sb.append(heartBeatBodyRequestDto.getBattary());
            sb.append(heartBeatBodyRequestDto.getErrcode());
            sb.append(heartBeatBodyRequestDto.getParkingStatu());
            String md5Str = DigestUtil.md5Hex(sb.toString().toUpperCase()).toUpperCase();
            if (!(heartBeatBodyRequestDto.getToken().equals(md5Str))) {
                return resultDto.makeResult(GatherResultEnum.TOKEN_ERR.getValue(),
                        GatherResultEnum.TOKEN_ERR.getComment());
            }
            //查询地磁
            MagneticDetectorGetByMacRequestDto magneticDetectorGetByMacRequestDto = new MagneticDetectorGetByMacRequestDto();
            magneticDetectorGetByMacRequestDto.setProvider(DeviceProviderEnum.FUSHANG.getValue());
            magneticDetectorGetByMacRequestDto.setSerialNumber(heartBeatBodyRequestDto.getDeviceID());
            ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = platformDeviceService.getMagneticDetectorByMac(magneticDetectorGetByMacRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                log.error("富尚地磁检测设备查询失败:错误的设备编号" + heartBeatBodyRequestDto.getDeviceID());
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            //是否关联泊位
            if (objectResultDto.getData().getParkingLotId() == 0) {
                return resultDto.makeResult(MagneticDetectorEnum.NO_CONNECTION_PARKING_LOT.getValue(), MagneticDetectorEnum
                        .NO_CONNECTION_PARKING_LOT.getComment());
            }
            //是否注册
            if (!objectResultDto.getData().getRegistered()) {
                return resultDto.makeResult(MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_REGISTERED.getValue(), MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_REGISTERED.getComment());
            }
            MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = objectResultDto.getData();
            MagneticHeartBeatAddRequestDto magneticHeartBeatAddRequestDto = new MagneticHeartBeatAddRequestDto();
            magneticHeartBeatAddRequestDto.setTenantId(magneticDetectorGetByMacResultDto.getTenantId());
            magneticHeartBeatAddRequestDto.setParkingId(magneticDetectorGetByMacResultDto.getParkingId());
            magneticHeartBeatAddRequestDto.setParkingLotId(magneticDetectorGetByMacResultDto.getParkingLotId());
            magneticHeartBeatAddRequestDto.setDetectorId(magneticDetectorGetByMacResultDto.getId());
            magneticHeartBeatAddRequestDto.setProvider(magneticDetectorGetByMacResultDto.getProvider());
            magneticHeartBeatAddRequestDto.setSerialNumber(heartBeatBodyRequestDto.getDeviceID());
            //占用状态（0 正常,1 异常）
            magneticHeartBeatAddRequestDto.setOccupyStatus(heartBeatBodyRequestDto.getParkingStatu());
            magneticHeartBeatAddRequestDto.setBeatTime(DateUtil.parse(heartBeatBodyRequestDto.getTime()));
            magneticHeartBeatAddRequestDto.setBattery(heartBeatBodyRequestDto.getBattary());
            magneticHeartBeatAddRequestDto.setCommandCode(requestDto.getCmd());
            magneticHeartBeatAddRequestDto.setErrorCode(heartBeatBodyRequestDto.getErrcode());
            magneticHeartBeatAddRequestDto.setCreationTime(new Date());
            //添加心跳
            ResultDto insertResult = magneticHeartBeatService.addMagneticHeartBeat(magneticHeartBeatAddRequestDto);
            if (insertResult.isSuccess()) {
                MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
                updateRequestDto.setLastHeartbeatTime(magneticHeartBeatAddRequestDto.getBeatTime());
                //地磁占用状态为占用和空闲，心跳维护占用状态为可用和异常，所以不进行维护
                updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
                updateRequestDto.setElectricity(heartBeatBodyRequestDto.getBattary());
                ResultDto dto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
                if (dto.isFailed()) {
                    return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                            GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
                }
            } else {
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getValue(),
                        GatherResultEnum.MAGNETIC_HEART_BEAT_INSERT_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("富尚心跳数据处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 推送注册数据处理
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SendResultSto fuShangRegisterPush(FuShangRegisterPushRequestDto requestDto) {
        SendResultSto resultDto = new SendResultSto();
        try {
            FuShangRegisterBodyRequestDto fuShangRegisterBodyRequestDto = requestDto.getBody();
            //校验和
            StringBuilder sb = new StringBuilder();
            sb.append(requestDto.getCmd());
            sb.append(fuShangRegisterBodyRequestDto.getParkID());
            sb.append(fuShangRegisterBodyRequestDto.getTime());
            sb.append(fuShangRegisterBodyRequestDto.getDeviceID());
            sb.append(fuShangRegisterBodyRequestDto.getVersion());
            String md5Str = DigestUtil.md5Hex(sb.toString().toUpperCase()).toUpperCase();
            if (!(fuShangRegisterBodyRequestDto.getToken().equals(md5Str))) {
                return resultDto.makeResult(GatherResultEnum.TOKEN_ERR.getValue(),
                        GatherResultEnum.TOKEN_ERR.getComment());
            }
            //查询地磁
            MagneticDetectorGetByMacRequestDto magneticDetectorGetByMacRequestDto = new MagneticDetectorGetByMacRequestDto();
            magneticDetectorGetByMacRequestDto.setProvider(DeviceProviderEnum.FUSHANG.getValue());
            magneticDetectorGetByMacRequestDto.setSerialNumber(fuShangRegisterBodyRequestDto.getDeviceID());
            ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = platformDeviceService.getMagneticDetectorByMac(magneticDetectorGetByMacRequestDto);
            if (objectResultDto.isFailed() || objectResultDto.getData() == null) {
                log.error("富尚地磁检测设备查询失败:错误的设备编号" + fuShangRegisterBodyRequestDto.getDeviceID());
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            //不能重复注册
            if (objectResultDto.getData().getRegistered()) {
                return resultDto.makeResult(MagneticDetectorEnum.MAGNETIC_DETECTOR_REGISTERED.getValue(), MagneticDetectorEnum
                        .MAGNETIC_DETECTOR_REGISTERED.getComment());
            }
            MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = objectResultDto.getData();
            MagneticDetectorUpdateLastHeartbeatTimeRequestDto updateRequestDto = new MagneticDetectorUpdateLastHeartbeatTimeRequestDto();
            updateRequestDto.setId(magneticDetectorGetByMacResultDto.getId());
            //注册时间
            updateRequestDto.setRegisterTime(DateUtil.parse(fuShangRegisterBodyRequestDto.getTime()));
            //设备版本号
            updateRequestDto.setVersionNumber(fuShangRegisterBodyRequestDto.getVersion());
            ResultDto dto = platformDeviceService.updateMagneticDetectorLastHeartbeatTime(updateRequestDto);
            if (dto.isFailed()) {
                return resultDto.makeResult(GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getValue(),
                        GatherResultEnum.MAGNETIC_DETECTOR_UPDATE_ERR.getComment());
            }
            resultDto.success();
        } catch (Exception e) {
            log.error("富尚注册数据处理失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }
}
