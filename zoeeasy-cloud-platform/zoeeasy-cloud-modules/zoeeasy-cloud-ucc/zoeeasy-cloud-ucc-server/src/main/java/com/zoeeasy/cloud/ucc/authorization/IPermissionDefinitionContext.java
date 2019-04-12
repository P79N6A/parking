package com.zoeeasy.cloud.ucc.authorization;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;

/**
 * 权限定义上下文
 *
 * @author walkman
 */
public interface IPermissionDefinitionContext {

    /**
     * 创建权限
     *
     * @param code
     * @param name
     * @param description
     * @param multiTenancySide
     * @return
     */
    Permission createPermission(String code, String name, String description, TenancyHostSide multiTenancySide);

    /**
     * Gets a permission with given name or null if can not find.
     *
     * @param code Unique name of the permission
     * @return Permission object or null
     */
    Permission getPermissionOrNull(String code);

    /**
     * Remove permission with given name
     *
     * @param code name
     */
    void removePermission(String code);
}
