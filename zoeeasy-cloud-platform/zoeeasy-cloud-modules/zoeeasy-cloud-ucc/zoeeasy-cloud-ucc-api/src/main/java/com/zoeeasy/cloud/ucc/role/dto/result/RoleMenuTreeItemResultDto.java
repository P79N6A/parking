package com.zoeeasy.cloud.ucc.role.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 角色菜单树视图模型
 *
 * @author walkman
 */
@ApiModel(value = "RoleMenuTreeItemResultDto", description = "角色菜单树视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuTreeItemResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    @ApiModelProperty("菜单ID")
    private Long menuId;

    /**
     * 菜单代码
     */
    @ApiModelProperty("菜单代码")
    private String code;

    /**
     * 菜单名称
     */
    @ApiModelProperty("菜单名称")
    private String name;

    /**
     * 上级菜单id
     */
    @ApiModelProperty("上级菜单id")
    private Long parentId;

    /**
     * 是否已授予
     */
    @ApiModelProperty("是否已授予")
    private Boolean granted;

    @JsonIgnore
    @ApiModelProperty("菜单路径")
    private String pathCode;

    /**
     * 子菜单列表
     */
    private List<RoleMenuTreeItemResultDto> children;
}
