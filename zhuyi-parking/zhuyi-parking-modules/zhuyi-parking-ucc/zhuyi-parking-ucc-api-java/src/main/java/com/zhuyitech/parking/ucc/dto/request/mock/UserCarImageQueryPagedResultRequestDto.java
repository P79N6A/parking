package com.zhuyitech.parking.ucc.dto.request.mock;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;


@ApiModel(value = "UserCarImageQueryPagedResultRequestDto", description = "获取车辆logo列表分页请求参数表")
public class UserCarImageQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}
