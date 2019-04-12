package com.zoeeasy.cloud.ucc.navigation.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户菜单项
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "UserMenuItem", description = "菜单项")
public class UserMenuItem extends BaseDto {

    /**
     * Unique code of the menu item in the application.
     * Can be used to find this menu item later.
     */
    @ApiModelProperty(value = "菜单编号", required = true)
    private String code;

    /**
     * name of the menu item. Required.
     */
    @ApiModelProperty(value = "菜单名称", required = true)
    private String name;

    /**
     * The Display order of the menu. Optional.
     */
    @ApiModelProperty(value = "排序")
    private Integer order;

    /**
     * Icon of the menu item if exists. Optional.
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * The URL to navigate when this menu item is selected. Optional.
     */
    @ApiModelProperty(value = "URL")
    private String url;

    /**
     * The VUE component to navigate when this menu item is selected
     */
    @ApiModelProperty(value = "Vue Component")
    private String component;

    /**
     * 菜单节点绝对层级
     */
    @ApiModelProperty(value = "菜单节点绝对层级")
    private String level;

    /**
     * Target of the menu item. Can be "_blank", "_self", "_parent", "_top" or a frame name.
     */
    @ApiModelProperty(value = "_blank|_self|_parent|_top")
    private String target;

    /**
     * Can be used to store a custom object related to this menu item. Optional.
     */
    @ApiModelProperty(value = "customData")
    private Object customData;

    /**
     * Can be used to enable/disable a menu item.
     */
    @ApiModelProperty(value = "是否可用")
    private Boolean enabled;

    /**
     * Can be used to show/hide a menu item.
     */
    @ApiModelProperty(value = "是否可见")
    private Boolean visible;

    /**
     * Sub items of this menu item. Optional.
     */
    @ApiModelProperty(value = "子菜单列表")
    private List<UserMenuItem> items;

    /**
     * Creates a newU serMenuItem object.
     *
     * @param menuItemDefinition menuItemDefinition
     */
    public UserMenuItem(MenuItemDefinition menuItemDefinition) {
        this.code = menuItemDefinition.getCode();
        this.name = menuItemDefinition.getName();
        this.icon = menuItemDefinition.getIcon();
        this.order = menuItemDefinition.getOrder();
        this.url = menuItemDefinition.getUrl();
        this.component = menuItemDefinition.getComponent();
        this.level = menuItemDefinition.getLevel();
        this.customData = menuItemDefinition.getCustomData();
        this.target = menuItemDefinition.getTarget();
        this.enabled = menuItemDefinition.getEnabled();
        this.visible = menuItemDefinition.getVisible();
        this.items = new ArrayList<>();
    }
}