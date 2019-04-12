package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import io.swagger.annotations.ApiModel;

/**
 * 用户解绑车请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(description = "用户解绑车请求参数")
public class UserCarInfoUnBindRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
