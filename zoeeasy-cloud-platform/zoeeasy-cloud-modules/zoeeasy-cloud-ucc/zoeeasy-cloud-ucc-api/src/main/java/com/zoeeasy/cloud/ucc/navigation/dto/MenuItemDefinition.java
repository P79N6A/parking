package com.zoeeasy.cloud.ucc.navigation.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import lombok.*;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单项定义
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuItemDefinition extends BaseDto implements IHasMenuItemDefinitions {

    /**
     * 菜单ID
     */
    private Long id;

    /**
     * Unique code of the menu item in the application.
     * Can be used to find this menu item later.
     */
    private String code;

    /**
     * name of the menu item. Required.
     */
    private String name;

    /**
     * The Display order of the menu. Optional.
     */
    private Integer order;

    /**
     * Icon of the menu item if exists. Optional.
     */
    private String icon;

    /**
     * level relative to the roo menu,start from index 1
     */
    private String level;

    /**
     * The URL to navigate when this menu item is selected. Optional.
     */
    private String url;

    /**
     * The VUE component to navigate when this menu item is selected
     */
    private String component;

    /**
     * A permission name. Only users that has this permission can see this menu item.
     * Optional.
     */
    private String requiredPermission;

    /**
     * This can be set to true if only authenticated users should see this menu item.
     */
    private Boolean requiresAuthenticate = false;

    /**
     * pathCode
     */
    private String pathCode;

    /**
     * TenancyHostSide
     */
    private TenancyHostSide tenancyHostSide;

    /**
     * Target of the menu item. Can be "_blank", "_self", "_parent", "_top" or a frame name.
     */
    private String target;

    /**
     * Can be used to store a custom object related to this menu item. Optional.
     */
    private Object customData;

    /**
     * Can be used to enable/disable a menu item.
     */
    private Boolean enabled;

    /**
     * Can be used to show/hide a menu item.
     */
    private Boolean visible;

    /**
     * Sub items of this menu item. Optional.
     */
    private List<MenuItemDefinition> items;

    /**
     * 是否是叶子节点菜单
     *
     * @return
     */
    public Boolean isLeaf() {
        return CollectionUtils.isEmpty(this.items);
    }

    /**
     * 添加菜单项
     *
     * @param menuItem
     * @return
     */
    public MenuItemDefinition addItem(MenuItemDefinition menuItem) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(menuItem);
        return this;
    }

    /**
     * 移除指定的菜单项
     *
     * @param code
     */
    public void removeItem(String code) {
        items.removeIf(m -> m.code.equals(code));
    }

    /**
     * 将指定的菜单项移到顶部
     *
     * @param menuItemName
     */
    public void moveMenuItemToTop(String menuItemName) {
        MenuItemDefinition menuItem = getMenuItem(menuItemName);
        this.items.remove(menuItem);
        this.items.add(0, menuItem);
    }

    /**
     * 将指定的菜单项移到底部
     *
     * @param menuItemName
     */
    public void moveMenuItemToBottom(String menuItemName) {
        MenuItemDefinition menuItem = getMenuItem(menuItemName);
        this.items.remove(menuItem);
        this.items.add(this.items.size(), menuItem);
    }

    /**
     * @param menuItemName
     * @param targetMenuItemName
     */
    public void moveMenuItemBefore(String menuItemName, String targetMenuItemName) {
        MenuItemDefinition menuItem = getMenuItem(menuItemName);
        MenuItemDefinition targetMenuItem = getMenuItem(targetMenuItemName);
        items.remove(menuItem);
        items.add(items.indexOf(targetMenuItem), menuItem);
    }

    /// <summary>
    /// Moves a menu item in the list before another menu item in the list.
    /// </summary>
    /// <param name="menuItems">List of menu items</param>
    /// <param name="menuItemName">Name of the menu item to move</param>
    /// <param name="targetMenuItemName">Target menu item (to move after it)</param>
    public void moveMenuItemAfter(String menuItemName, String targetMenuItemName) {
        MenuItemDefinition menuItem = getMenuItem(menuItemName);
        MenuItemDefinition targetMenuItem = getMenuItem(targetMenuItemName);
        this.items.remove(menuItem);
        this.items.add(this.items.indexOf(targetMenuItem) + 1, menuItem);
    }

    private MenuItemDefinition getMenuItem(String menuItemName) {
        return this.getItems().stream().filter(item -> menuItemName.equals(item.getCode()))
                .findFirst().orElse(null);
    }
}
