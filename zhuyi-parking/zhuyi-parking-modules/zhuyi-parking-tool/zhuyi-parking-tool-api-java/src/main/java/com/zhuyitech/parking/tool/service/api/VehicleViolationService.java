package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryByCarRequestDto;
import com.zhuyitech.parking.tool.dto.request.violation.VehicleViolationQueryRequestDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationCategoryResultDto;
import com.zhuyitech.parking.tool.dto.result.volation.VehicleViolationResultDto;

/**
 * 车辆违章服务
 *
 * @author walkman
 * @Date: 2018/4/14
 */
public interface VehicleViolationService {

    /**
     * 车辆违章查询
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<VehicleViolationResultDto> queryVehicleViolation(VehicleViolationQueryRequestDto requestDto);

    /**
     * 查询本地车辆违章记录
     *
     * @param requestDto 请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<VehicleViolationCategoryResultDto> queryLocalVehicleViolation(VehicleViolationQueryByCarRequestDto requestDto);


}
