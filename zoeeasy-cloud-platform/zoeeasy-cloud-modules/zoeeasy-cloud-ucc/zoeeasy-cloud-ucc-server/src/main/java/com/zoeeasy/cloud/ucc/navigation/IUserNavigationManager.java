package com.zoeeasy.cloud.ucc.navigation;

import com.scapegoat.infrastructure.core.UserIdentifier;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenu;

import java.util.List;

/**
 * 用户菜单资源管理
 *
 * @author walkman
 */
public interface IUserNavigationManager {

    /**
     * Gets a menu specialized for given user.
     *
     * @param menuCode
     * @param userIdentifier
     * @return UserMenu
     */
    UserMenu getUserMenu(UserIdentifier userIdentifier, String menuCode);

    /**
     * Gets all menus specialized for given user.
     *
     * @param user
     * @return
     */
    List<UserMenu> getAllUserMenus(UserIdentifier user);
}
