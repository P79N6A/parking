package com.zoeeasy.cloud.ucc.navigation;

import com.zoeeasy.cloud.ucc.navigation.dto.MenuDefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 菜单资源管理接口
 *
 * @author walkman
 */
public class NavigationManager implements INavigationManager, ApplicationContextAware {

    private Map<String, MenuDefinition> menus;

    private MenuDefinition mainMenu;

    private ApplicationContext applicationContext;

    public NavigationManager() {
        MenuDefinition mainMenuDefinition = new MenuDefinition("MainMenu", "", null, null);
        menus = new HashMap<>();
        menus.put("MainMenu", mainMenuDefinition);
        mainMenu = mainMenuDefinition;
    }

    @Override
    public Map<String, MenuDefinition> getMenus() {
        return menus;
    }

    public void setMenus(Map<String, MenuDefinition> menus) {
        this.menus = menus;
    }

    @Override
    public MenuDefinition getMainMenu() {
        return mainMenu;
    }

    public void setMainMenu(MenuDefinition mainMenu) {
        this.mainMenu = mainMenu;
    }

    /**
     * 初始化
     */
    public void initialize() {

        NavigationProviderContext navigationProviderContext = new NavigationProviderContext(this);
        Map<String, NavigationProvider> beansOfType = getApplicationContext().getBeansOfType(NavigationProvider.class);
        if (!ObjectUtils.isEmpty(beansOfType)) {
            Iterator<Map.Entry<String, NavigationProvider>> ite = beansOfType.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, NavigationProvider> entry = ite.next();
                NavigationProvider navigationProvider = entry.getValue();
                navigationProvider.setNavigation(navigationProviderContext);
            }
        }
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
