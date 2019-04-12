package com.zoeeasy.cloud.core;

import java.util.List;

/**
 * 角色管理配置
 *
 * @author walkman
 */
public interface IRoleManagementConfig {

    /**
     * 获取静态角色
     *
     * @return
     */
    List<StaticRoleDefinition> getStaticRoles();

    /**
     * 获取宿主静态角色
     *
     * @return
     */
    List<StaticRoleDefinition> getHostStaticRoles();

    /**
     * 获取租户静态角色
     *
     * @return
     */
    List<StaticRoleDefinition> getTenantStaticRoles();
}
