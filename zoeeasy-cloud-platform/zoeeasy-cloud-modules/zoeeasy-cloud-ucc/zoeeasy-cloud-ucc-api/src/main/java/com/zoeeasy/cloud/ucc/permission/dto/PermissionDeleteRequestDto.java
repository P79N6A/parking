package com.zoeeasy.cloud.ucc.permission.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;


/**
 * 删除权限请求参数
 *
 * @author walkman
 */
@ApiModel(description = "删除权限请求参数")
public class PermissionDeleteRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

}
