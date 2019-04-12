package com.zoeeasy.cloud.ucc.navigation;

/**
 * 导航菜单提供上下文
 *
 * @author walkman
 */
public class NavigationProviderContext implements INavigationProviderContext {

    private INavigationManager navigationManager;

    public NavigationProviderContext(INavigationManager manager) {
        navigationManager = manager;
    }

    @Override
    public INavigationManager getNavigationManager() {
        return navigationManager;
    }

    public void setNavigationManager(INavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }
}
