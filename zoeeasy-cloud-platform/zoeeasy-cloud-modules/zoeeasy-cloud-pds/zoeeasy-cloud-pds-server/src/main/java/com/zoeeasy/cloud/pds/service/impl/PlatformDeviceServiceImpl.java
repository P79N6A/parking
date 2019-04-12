package com.zoeeasy.cloud.pds.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.device.PlatformDeviceService;
import com.zoeeasy.cloud.pds.domain.MagneticDetectorEntity;
import com.zoeeasy.cloud.pds.enums.MagneticDetectorEnum;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorGetByMacRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorUpdateLastHeartbeatTimeRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorStatusUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.airjoy.MagneticDetectorGetByMacResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorStatusUpdateRequestDtoValidator;
import com.zoeeasy.cloud.pds.magneticdetector.validator.MagneticDetectorUpdateLastHeartbeatTimeRequestDtoValidator;
import com.zoeeasy.cloud.pds.service.MagneticDetectorCrudService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 平台设备管理服务
 *
 * @author walkman
 */
@Service("platformDeviceService")
@Slf4j
public class PlatformDeviceServiceImpl implements PlatformDeviceService {

    @Autowired
    private MagneticDetectorCrudService magneticDetectorCrudService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * 根据厂商和设备序列号查询对应的地磁检测器
     *
     * @param requestDto
     * @return
     */
    @Override
    public ObjectResultDto<MagneticDetectorGetByMacResultDto> getMagneticDetectorByMac(MagneticDetectorGetByMacRequestDto requestDto) {
        ObjectResultDto<MagneticDetectorGetByMacResultDto> objectResultDto = new ObjectResultDto<>();
        try {
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.findMagneticDetectorByProviderAndSerialNumber(requestDto.getSerialNumber(), requestDto.getProvider());
            if (magneticDetectorEntity != null) {
                MagneticDetectorGetByMacResultDto magneticDetectorGetByMacResultDto = modelMapper.map(magneticDetectorEntity, MagneticDetectorGetByMacResultDto.class);
                objectResultDto.setData(magneticDetectorGetByMacResultDto);
            }
            objectResultDto.success();
        } catch (Exception e) {
            log.error("获取地磁检测器失败" + e.getMessage());
            objectResultDto.failed();
        }
        return objectResultDto;
    }

    /**
     * 维护地磁检测器心跳时间及注册状态
     *
     * @param requestDto
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto updateMagneticDetectorLastHeartbeatTime(@FluentValid(MagneticDetectorUpdateLastHeartbeatTimeRequestDtoValidator.class) MagneticDetectorUpdateLastHeartbeatTimeRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticDetectorEntity magneticDetectorEntity = magneticDetectorCrudService.findMagneticDetectorById(requestDto.getId());
            if (magneticDetectorEntity == null) {
                return resultDto.makeResult(MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_FOUND.getValue(),
                        MagneticDetectorEnum.MAGNETIC_DETECTOR_NOT_FOUND.getComment());
            }
            if (requestDto.getLastHeartbeatTime() != null) {
                magneticDetectorEntity.setLastHeartbeatTime(requestDto.getLastHeartbeatTime());
            }
            if (requestDto.getOccupyStatus() != null) {
                magneticDetectorEntity.setOccupyStatus(requestDto.getOccupyStatus());
            }
            if (requestDto.getElectricity() != null) {
                magneticDetectorEntity.setElectricity(requestDto.getElectricity());
            }
            //注册时间
            if (requestDto.getRegisterTime() != null) {
                magneticDetectorEntity.setRegisterTime(requestDto.getRegisterTime());
            }
            //设备版本号
            if (requestDto.getVersionNumber() != null) {
                magneticDetectorEntity.setVersionNumber(requestDto.getVersionNumber());
            }
            if (!magneticDetectorEntity.getRegistered()) {
                magneticDetectorEntity.setRegistered(Boolean.TRUE);
            }
            magneticDetectorCrudService.updateMagneticDetectorLastHeartbeatTime(magneticDetectorEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("设备更新失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

    /**
     * 修改地磁检测器状态
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto updateMagneticDetectorStatus(@FluentValid(MagneticDetectorStatusUpdateRequestDtoValidator.class) MagneticDetectorStatusUpdateRequestDto requestDto) {
        ResultDto resultDto = new ResultDto();
        try {
            MagneticDetectorEntity magneticDetectorEntity = modelMapper.map(requestDto, MagneticDetectorEntity.class);
            magneticDetectorCrudService.updateMagneticDetectorStatus(magneticDetectorEntity);
            resultDto.success();
        } catch (Exception e) {
            log.error("修改地磁检测器状态失败" + e.getMessage());
            resultDto.failed();
        }
        return resultDto;
    }

}
