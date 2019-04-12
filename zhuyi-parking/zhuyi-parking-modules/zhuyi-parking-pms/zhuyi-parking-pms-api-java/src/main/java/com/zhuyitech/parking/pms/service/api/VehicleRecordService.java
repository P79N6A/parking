package com.zhuyitech.parking.pms.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordAddRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordGetRequestDto;
import com.zhuyitech.parking.pms.dto.request.vehicle.VehicleRecordUpdateRequestDto;
import com.zhuyitech.parking.pms.dto.result.VehicleRecordResultDto;

/**
 * 平台车辆服务
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
public interface VehicleRecordService {

    /**
     * 添加平台车辆
     *
     * @param requestDto VehicleRecordAddRequestDto
     * @return ResultDto
     */
    ResultDto addVehicleRecord(VehicleRecordAddRequestDto requestDto);

    /**
     * 修改平台车辆
     *
     * @param requestDto VehicleRecordUpdateRequestDto
     * @return ResultDto
     */
    ResultDto updateVehicleRecord(VehicleRecordUpdateRequestDto requestDto);

    /**
     * 获取平台车辆
     *
     * @param requestDto VehicleRecordGetRequestDto
     * @return VehicleRecordResultDto
     */
    ObjectResultDto<VehicleRecordResultDto> getVehicleRecord(VehicleRecordGetRequestDto requestDto);
}

