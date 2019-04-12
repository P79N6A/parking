package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.SpecialTypeGetRequestDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleInfoResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.SpecialVehicleTypeResultDto;

/**
 * 特殊车辆服务
 *
 * @author AkeemSuper
 * @date 2018/10/26 0026
 */
public interface SpecialVehicleService {

    /**
     * 通过车牌号获取特殊车辆类型(无租户)
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SpecialVehicleTypeResultDto> getSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto);

    /**
     * 带租户获取特殊车辆类型
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SpecialVehicleTypeResultDto> findSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto);

    /**
     * 无租户获取特殊车辆类型和有效时间
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<SpecialVehicleInfoResultDto> selectSpecialTypeByPlateNo(SpecialTypeGetRequestDto requestDto);
}
