package com.zoeeasy.cloud.ucc.role.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 角色权限树视图模型
 *
 * @author walkman
 */
@ApiModel(value = "RolePermissionTreeItemResultDto", description = "角色权限树视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermissionTreeItemResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 权限ID
     */
    @ApiModelProperty("权限ID")
    private Long permissionId;

    /**
     * 权限代码
     */
    @ApiModelProperty("权限代码")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty("权限名称")
    private String name;

    /**
     * 上级权限id
     */
    @ApiModelProperty("上级权限ID")
    private Long parentId;

    /**
     * 是否已授予
     */
    @ApiModelProperty("是否已授予")
    private Boolean granted;

    /**
     * 子权限列表
     */
    private List<RolePermissionTreeItemResultDto> children;
}
