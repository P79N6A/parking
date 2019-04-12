package com.zhuyitech.parking.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


@ApiModel(value = "UserPortraitQueryPagedResultRequestDto", description = "获取车辆logo列表分页请求参数表")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPortraitQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}