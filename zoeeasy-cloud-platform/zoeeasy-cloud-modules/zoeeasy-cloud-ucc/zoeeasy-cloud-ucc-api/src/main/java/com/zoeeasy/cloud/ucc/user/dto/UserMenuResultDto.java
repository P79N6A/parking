package com.zoeeasy.cloud.ucc.user.dto;

import com.scapegoat.infrastructure.core.dto.result.AuditedEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户菜单视图模型
 *
 * @author walkman
 */
@ApiModel(value = "UserMenuResultDto", description = "用户菜单视图模型")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserMenuResultDto extends AuditedEntityDto<Long> {

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
     * 菜单URL
     */
    @ApiModelProperty("菜单URL")
    private String url;

    /**
     * 菜单类型
     */
    @ApiModelProperty("菜单URL")
    private String type;

    /**
     * 组件名称
     */
    @ApiModelProperty(value = "component")
    private String component;

    /**
     * 菜单图标
     */
    @ApiModelProperty("菜单图标")
    private String icon;

    /**
     * 是否显示 1,显示;2,不显示
     */
    @ApiModelProperty("是否显示")
    private Boolean shown;

    /**
     * 上级菜单id
     */
    @ApiModelProperty("上级菜单id")
    private Long parentId;

    /**
     * 权限标识
     */
    @ApiModelProperty("权限标识")
    private String permission;


    /**
     * level
     */
    @ApiModelProperty(value = "level")
    private String level;

    /**
     * 排序
     */
    @ApiModelProperty(value = "sort")
    private Integer sort;

    /**
     * 父路径
     */
    @ApiModelProperty(value = "pathCode")
    private String pathCode;

    /**
     * 是否静态菜单
     */
    @ApiModelProperty(value = "staticMenu")
    private Boolean staticMenu;

    /**
     * 状态
     */
    @ApiModelProperty(value = "status")
    private Integer status;

    /**
     *
     */
    @ApiModelProperty(value = "tenancyHostSide")
    private Integer tenancyHostSide;

    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remarks;
}