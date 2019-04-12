package com.zoeeasy.cloud.ucc.role.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermissionListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空")
    @ApiModelProperty(value = "roleId", required = true)
    private Long roleId;

    /**
     * 权限ID列表
     */
    @ApiModelProperty(value = "权限ID列表")
    private List<Long> permissionIds;

}
