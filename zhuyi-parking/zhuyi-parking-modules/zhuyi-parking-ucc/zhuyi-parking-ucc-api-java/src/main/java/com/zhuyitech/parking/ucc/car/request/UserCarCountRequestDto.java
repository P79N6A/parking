package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;

/**
 * 用户车辆个数请求参数
 */
@ApiModel(value = "UserCarCountRequestDto", description = "用户车辆个数请求参数")
public class UserCarCountRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
