package com.zhuyitech.parking.tool.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.PlateNumberCheckRequestDto;
import com.zhuyitech.parking.tool.dto.result.vehicle.PlateNumberCheckResultDto;

/**
 * @author walkman
 */
public interface VehicleVerifyService {

    /**
     * 第三方校验车牌信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<PlateNumberCheckResultDto> vehicleVerify(PlateNumberCheckRequestDto requestDto);
}