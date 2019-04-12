package com.zoeeasy.cloud.ucc.role.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.ucc.role.cst.RoleConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 角色授权请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleAuthorizeRequestDto", description = "角色授权请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleAuthorizeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "角色ID", required = true)
    @NotNull(message = RoleConstant.ROLE_ID_EMPTY)
    private Long roleId;

    /**
     * 菜单ID列表
     */
    private List<Long> menus;

    /**
     * 权限ID列表
     */
    private List<Long> permissions;
}