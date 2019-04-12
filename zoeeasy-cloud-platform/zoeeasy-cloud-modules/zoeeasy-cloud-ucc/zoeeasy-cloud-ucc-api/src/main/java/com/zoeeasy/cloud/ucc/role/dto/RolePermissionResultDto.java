package com.zoeeasy.cloud.ucc.role.dto;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 角色权限视图模型
 *
 * @author walkman
 */
@ApiModel(value = "RolePermissionResultDto", description = "角色权限视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class RolePermissionResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty("角色ID")
    private Long roleId;

    /**
     * 权限代码
     */
    @ApiModelProperty("权限代码")
    private String code;

    /**
     * 权限名称
     */
    @ApiModelProperty("角色名称")
    private String name;

    /**
     *
     */
    @ApiModelProperty("sort")
    private Integer sort;

    /**
     *
     */
    @ApiModelProperty("status")
    private Integer status;

    /**
     *
     */
    @ApiModelProperty("enabled")
    private Integer enabled;

    /**
     *
     */
    @ApiModelProperty("description")
    private String description;

}