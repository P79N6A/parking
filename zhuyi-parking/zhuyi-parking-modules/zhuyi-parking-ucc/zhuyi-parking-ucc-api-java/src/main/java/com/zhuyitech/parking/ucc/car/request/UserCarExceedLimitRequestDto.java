package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import io.swagger.annotations.ApiModel;

/**
 * 用户车辆是否超过限定
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(description = "用户车辆是否超过限定数量请求参数")
public class UserCarExceedLimitRequestDto extends SessionDto {
    private static final long serialVersionUID = 1L;

}
