package com.zoeeasy.cloud.ucc.navigation;

/**
 * This interface should be implemented by classes which change
 * navigation of the application.
 */
public abstract class NavigationProvider {

    /**
     * Used to set navigation
     *
     * @param context Navigation context
     */
    public abstract void setNavigation(INavigationProviderContext context);
}
