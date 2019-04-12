package com.zoeeasy.cloud.core;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;

/**
 * 应用角色配置
 */
public final class AppRoleConfig {

    private AppRoleConfig() {
    }

    /**
     * 配置静态角色
     *
     * @param roleManagementConfig
     */
    public static void configure(IRoleManagementConfig roleManagementConfig) {

    }

    /**
     * 配置默认的静态角色
     *
     * @param roleManagementConfig
     */
    public static void configureDefault(IRoleManagementConfig roleManagementConfig) {
        //宿主端静态角色
        roleManagementConfig.getStaticRoles().add(
                new StaticRoleDefinition(
                        StaticRoles.Host.ADMIN_ID,
                        StaticRoles.Host.ADMIN,
                        StaticRoles.Host.ADMIN_NAME,
                        Boolean.FALSE,
                        TenancyHostSide.Host)
        );
        roleManagementConfig.getStaticRoles().add(
                new StaticRoleDefinition(
                        StaticRoles.Host.USER_ID,
                        StaticRoles.Host.USER,
                        StaticRoles.Host.USER_NAME,
                        Boolean.TRUE,
                        TenancyHostSide.Host)
        );
        //租户端静态角色
        roleManagementConfig.getStaticRoles().add(
                new StaticRoleDefinition(
                        StaticRoles.Tenants.ADMIN_ID,
                        StaticRoles.Tenants.ADMIN,
                        StaticRoles.Tenants.ADMIN_NAME,
                        Boolean.FALSE,
                        TenancyHostSide.Tenant)
        );
        roleManagementConfig.getStaticRoles().add(
                new StaticRoleDefinition(
                        StaticRoles.Tenants.USER_ID,
                        StaticRoles.Tenants.USER,
                        StaticRoles.Tenants.USER_NAME,
                        Boolean.TRUE,
                        TenancyHostSide.Tenant)
        );
    }
}