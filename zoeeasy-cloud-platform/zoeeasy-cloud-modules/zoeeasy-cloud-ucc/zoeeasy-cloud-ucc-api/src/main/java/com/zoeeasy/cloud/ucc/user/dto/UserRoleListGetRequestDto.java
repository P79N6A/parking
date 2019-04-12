package com.zoeeasy.cloud.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 用户角色列表获取请求参数
 *
 * @author walkman
 */
@ApiModel(value = "UserRoleListGetRequestDto", description = "用户角色列表获取请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "userId", required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;

}
