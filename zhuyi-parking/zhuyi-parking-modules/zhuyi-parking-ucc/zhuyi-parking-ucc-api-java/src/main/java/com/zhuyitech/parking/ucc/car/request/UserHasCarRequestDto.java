package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import io.swagger.annotations.ApiModel;

/**
 * 用户是否绑定车辆请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(value = "UserHasCarRequestDto", description = "用户是否绑定车辆请求参数")
public class UserHasCarRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;
}
