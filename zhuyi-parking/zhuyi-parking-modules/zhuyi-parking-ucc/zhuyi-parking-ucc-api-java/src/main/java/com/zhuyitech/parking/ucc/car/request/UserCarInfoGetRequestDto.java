package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;

/**
 * 获取用户车辆请求参数
 *
 * @author yuzhicheng
 */
@ApiModel(value = "UserCarInfoGetRequestDto", description = "获取用户车型请求参数")
public class UserCarInfoGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
