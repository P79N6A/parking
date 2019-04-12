package com.zoeeasy.cloud.ucc.role.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 分页获取角色菜单列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "RoleMenuQueryPagedResultRequestDto", description = "分页获取角色菜单列表请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuQueryPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID", required = true)
    private Long roleId;

    /**
     * 菜单ID列表
     */
    @ApiModelProperty(value = "菜单ID列表", dataType = "List<Long>")
    private List<Long> menuIds;

    /**
     * 菜单ID列表
     */
    @ApiModelProperty("菜单ID列表")
    private List<Long> menuParentIds;

    /**
     * 菜单是否显示
     */
    @ApiModelProperty("菜单是否显示")
    private Integer shown;

    /**
     * 是否加载子菜单
     */
    @ApiModelProperty("是否加载子菜单")
    private Boolean includeChild;

}
