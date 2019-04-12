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
 * 用户菜单
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ApiModel(value = "UserMenuItem", description = "菜单项")
public class UserMenu extends BaseDto {

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
     * Can be used to store a custom object related to this menu item. Optional.
     */
    @ApiModelProperty(value = "自定义数据(JSON)")
    private Object customData;

    /**
     * Sub items of this menu item. Optional.
     */
    @ApiModelProperty(value = "子菜单列表(JSON)")
    private List<UserMenuItem> items;

    /**
     * Creates a new UserMenu object from given MenuDefinition.
     *
     * @param menuDefinition
     */
    public UserMenu(MenuDefinition menuDefinition) {
        this.code = menuDefinition.getCode();
        this.name = menuDefinition.getName();
        this.customData = menuDefinition.getCustomData();
        this.items = new ArrayList<>();
    }
}
