package com.zoeeasy.cloud.ucc.authorization;

/**
 * 应用程序权限定义提供者
 *
 * @author walkman
 * @since 2018-10-20
 */
public abstract class AbstractAuthorizationProvider {

    /**
     * 权限定义,仅在应用程序初始化时定义权限
     *
     * @param context
     */
    public abstract void setPermissions(IPermissionDefinitionContext context);
}
