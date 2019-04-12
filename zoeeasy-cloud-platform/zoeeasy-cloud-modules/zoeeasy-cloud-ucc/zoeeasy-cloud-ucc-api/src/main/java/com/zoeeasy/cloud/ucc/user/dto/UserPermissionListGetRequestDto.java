package com.zoeeasy.cloud.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户权限列表获取请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserPermissionListGetRequestDto", description = "用户权限列表获取请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPermissionListGetRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

}