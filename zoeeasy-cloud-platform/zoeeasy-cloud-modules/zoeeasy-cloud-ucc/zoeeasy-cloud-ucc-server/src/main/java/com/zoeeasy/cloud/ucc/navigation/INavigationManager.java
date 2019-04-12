package com.zoeeasy.cloud.ucc.navigation;

import com.zoeeasy.cloud.ucc.navigation.dto.MenuDefinition;

import java.util.Map;

/**
 * 菜单资源管理接口
 *
 * @author walkman
 */
public interface INavigationManager {

    /**
     * All menus defined in the application.
     *
     * @return
     */
    Map<String, MenuDefinition> getMenus();

    /**
     * Gets the main menu of the application.
     *
     * @return
     */
    MenuDefinition getMainMenu();
}
