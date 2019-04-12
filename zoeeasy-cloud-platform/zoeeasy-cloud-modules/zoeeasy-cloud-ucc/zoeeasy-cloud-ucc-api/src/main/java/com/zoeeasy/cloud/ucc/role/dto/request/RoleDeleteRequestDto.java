package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionEntityDto;
import io.swagger.annotations.ApiModel;

/**
 * 删除角色请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleDeleteRequestDto", description = "删除角色请求参数")
public class RoleDeleteRequestDto extends SignedSessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;
}
