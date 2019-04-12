package com.zoeeasy.cloud.pds.device;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorGetByMacRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.airjoy.MagneticDetectorUpdateLastHeartbeatTimeRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.request.park.MagneticDetectorStatusUpdateRequestDto;
import com.zoeeasy.cloud.pds.magneticdetector.dto.result.airjoy.MagneticDetectorGetByMacResultDto;

/**
 * 平台设备管理服务
 *
 * @author walkman
 */
public interface PlatformDeviceService {


    /**
     * 根据厂商和设备序列号查询对应的地磁检测器
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<MagneticDetectorGetByMacResultDto> getMagneticDetectorByMac(MagneticDetectorGetByMacRequestDto requestDto);

    /**
     * 维护地磁检测器心跳时间
     *
     * @param requestDto
     * @return
     */
    ResultDto updateMagneticDetectorLastHeartbeatTime(MagneticDetectorUpdateLastHeartbeatTimeRequestDto requestDto);

    /**
     * 修改地磁检测器状态
     *
     * @param requestDto
     * @return
     */
    ResultDto updateMagneticDetectorStatus(MagneticDetectorStatusUpdateRequestDto requestDto);
}
