package com.zoeeasy.cloud.ucc.navigation;

import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.scapegoat.infrastructure.core.UserIdentifier;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.ucc.navigation.dto.MenuDefinition;
import com.zoeeasy.cloud.ucc.navigation.dto.MenuItemDefinition;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenu;
import com.zoeeasy.cloud.ucc.navigation.dto.UserMenuItem;
import com.zoeeasy.cloud.ucc.user.UserService;
import com.zoeeasy.cloud.ucc.user.dto.UserMenuListGetRequestDto;
import com.zoeeasy.cloud.ucc.user.dto.UserMenuResultDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户菜单资源管理
 *
 * @author walkman
 */
@Component
public class UserNavigationManager implements IUserNavigationManager {

    private final INavigationManager navigationManager;

    @Autowired
    private UserService userService;

    public UserNavigationManager(INavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    /**
     * Gets a menu specialized for given user.
     *
     * @param menuCode
     * @param userIdentifier
     * @return UserMenu
     */
    @Override
    public UserMenu getUserMenu(UserIdentifier userIdentifier, String menuCode) {
        MenuDefinition menuDefinition = navigationManager.getMenus().getOrDefault(menuCode, null);
        if (menuDefinition == null) {
            return null;
        }
        List<UserMenuResultDto> userMenuResultDtoList = loadUserMenuList(userIdentifier);
        if (CollectionUtils.isNotEmpty(userMenuResultDtoList)) {
            UserMenu userMenu = new UserMenu(menuDefinition);
            fillUserMenuItems(userMenuResultDtoList, menuDefinition.getItems(), userMenu.getItems(), "'");
            return userMenu;
        }
        return null;
    }

    /**
     * @param userIdentifier
     * @param userMenuResultDtoList
     * @return
     */
    private UserMenu getUserMenu(UserIdentifier userIdentifier, List<UserMenuResultDto> userMenuResultDtoList, String menuCode) {
        MenuDefinition menuDefinition = navigationManager.getMenus().getOrDefault(menuCode, null);
        if (menuDefinition == null) {
            return null;
        }
        UserMenu userMenu = new UserMenu(menuDefinition);
        fillUserMenuItems(userMenuResultDtoList, menuDefinition.getItems(), userMenu.getItems(), "");
        return userMenu;
    }

    /**
     * 获取用户所有菜单
     *
     * @param user
     * @return
     */
    @Override
    public List<UserMenu> getAllUserMenus(UserIdentifier user) {
        List<UserMenu> userMenus = new ArrayList<>();
        List<UserMenuResultDto> userMenuResultDtoList = loadUserMenuList(user);
        if (CollectionUtils.isNotEmpty(userMenuResultDtoList)) {
            for (MenuDefinition menu : navigationManager.getMenus().values()) {
                userMenus.add(getUserMenu(user, userMenuResultDtoList, menu.getCode()));
            }
        }
        return userMenus;
    }

    /**
     * Gets a menu specialized for given user.
     *
     * @param menuCode
     * @param userMenuList
     * @return UserMenu
     */
    public UserMenu getUserMenu(List<UserMenuResultDto> userMenuList, String menuCode) {
        MenuDefinition menuDefinition = navigationManager.getMenus().getOrDefault(menuCode, null);
        if (menuDefinition == null) {
            return null;
        }
        UserMenu userMenu = new UserMenu(menuDefinition);
        fillUserMenuItems(userMenuList, menuDefinition.getItems(), userMenu.getItems(), "");
        return userMenu;
    }

    /**
     * @param userMenuList
     * @param menuItemDefinitions
     * @param userMenuItems
     * @param parentLevel         层级深度
     */
    private int fillUserMenuItems(List<UserMenuResultDto> userMenuList, List<MenuItemDefinition> menuItemDefinitions, List<UserMenuItem> userMenuItems,
                                  String parentLevel) {
        int addedMenuItemCount = 0;
        for (MenuItemDefinition menuItemDefinition : menuItemDefinitions) {
            //如果用户有访问权限切菜单可见
            if (userMenuList.stream().anyMatch(item -> {
                return item.getId().equals(menuItemDefinition.getId()) && item.getShown();
            })) {

                UserMenuItem userMenuItem = new UserMenuItem(menuItemDefinition);

                //这里先加一，构造下级层级树,如果没有新的节点添加，然后再减一
                ++addedMenuItemCount;
                //ParentLevel累加
                String currentLevel = parentLevel;
                if (StringUtils.isNotEmpty(currentLevel)) {
                    currentLevel = currentLevel + "." + String.valueOf(addedMenuItemCount);
                } else {
                    currentLevel = String.valueOf(addedMenuItemCount);
                }
                if (menuItemDefinition.isLeaf() ||
                        (
                                fillUserMenuItems(userMenuList, menuItemDefinition.getItems(), userMenuItem.getItems(), currentLevel)
                        ) > 0) {

                    userMenuItem.setLevel(currentLevel);
                    userMenuItems.add(userMenuItem);
                } else {
                    --addedMenuItemCount;
                }
            }
        }
        return addedMenuItemCount;
    }

    /**
     * 加载用户有访问权限的菜单列表
     *
     * @param userIdentifier
     * @return
     */
    private List<UserMenuResultDto> loadUserMenuList(UserIdentifier userIdentifier) {
        //获取用户所有菜单列表
        UserMenuListGetRequestDto requestDto = new UserMenuListGetRequestDto();
        requestDto.setUserId(userIdentifier.getUserId());
        ListResultDto<UserMenuResultDto> userMenuResultDtoList = this.userService.getUserMenuList(requestDto);
        if (userMenuResultDtoList.isSuccess() && CollectionUtils.isNotEmpty(userMenuResultDtoList.getItems())) {
            return userMenuResultDtoList.getItems();
        }
        return null;
    }
}
