package com.zoeeasy.cloud.ucc.navigation;

/**
 * Provides infrastructure to set navigation.
 *
 * @author walkman
 */
public interface INavigationProviderContext {

    /**
     * Gets a reference to the menu manager.
     *
     * @return INavigationManager
     */
    INavigationManager getNavigationManager();
}
