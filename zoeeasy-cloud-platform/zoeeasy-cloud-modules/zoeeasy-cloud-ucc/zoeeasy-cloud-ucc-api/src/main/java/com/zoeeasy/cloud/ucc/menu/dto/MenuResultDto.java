package com.zoeeasy.cloud.ucc.menu.dto;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 菜单视图模型
 *
 * @author walkman
 */
@ApiModel(value = "MenuResultDto", description = "菜单视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class MenuResultDto extends AuditedEntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
     * 类型
     */
    @ApiModelProperty("类型")
    private Integer type;

    /**
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;

    /**
     * 组件名称
     */
    @ApiModelProperty("组件名称")
    private String component;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * level
     */
    @ApiModelProperty("level")
    private String level;

    /**
     * sort
     */
    @ApiModelProperty("sort")
    private Integer sort;

    /**
     * 上级菜单id
     */
    @ApiModelProperty("上级菜单id")
    private Long parentId;

    /**
     * pathCode
     */
    @ApiModelProperty("pathCode")
    private String pathCode;

    /**
     * 是否静态菜单
     */
    @ApiModelProperty("staticMenu")
    private Boolean staticMenu;

    /**
     * 是否显示 1,显示;2,不显示
     */
    @ApiModelProperty("是否显示")
    private Boolean shown;

    /**
     * 权限标识
     */
    @ApiModelProperty("权限标识")
    private String permission;

    /**
     * sort
     */
    @ApiModelProperty("status")
    private Integer status;

    /**
     * tenancyHostSide
     */
    @ApiModelProperty("tenancyHostSide")
    private Integer tenancyHostSide;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;


}
