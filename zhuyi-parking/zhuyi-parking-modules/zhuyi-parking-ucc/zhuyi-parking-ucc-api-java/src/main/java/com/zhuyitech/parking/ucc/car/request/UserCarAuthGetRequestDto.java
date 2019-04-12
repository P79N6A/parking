package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import io.swagger.annotations.ApiModel;

/**
 * 获取车辆认证
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@ApiModel(description = "获取车辆认证")
public class UserCarAuthGetRequestDto extends SessionEntityDto<Long> {
    private static final long serialVersionUID = 1L;
}
