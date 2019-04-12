package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;

/**
 * 当前用户车辆信息请求参数
 *
 * @author yuzhicheng
 */
@ApiModel(value = "CurrentUserCarGetRequestDto", description = "当前用户车辆信息请求参数")
public class CurrentUserCarGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
