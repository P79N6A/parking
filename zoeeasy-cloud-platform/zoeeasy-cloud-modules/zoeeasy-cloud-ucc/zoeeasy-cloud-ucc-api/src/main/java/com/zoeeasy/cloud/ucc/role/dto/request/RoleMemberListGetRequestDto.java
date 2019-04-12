package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取角色授予用户请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleMemberListGetRequestDto", description = "获取角色授予用户请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMemberListGetRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = RoleConstant.ROLE_ID_EMPTY)
    private Long roleId;

    /**
     * 部门ID
     */
    @ApiModelProperty(value = "部门ID")
    private Long organizationId;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String userName;

}
