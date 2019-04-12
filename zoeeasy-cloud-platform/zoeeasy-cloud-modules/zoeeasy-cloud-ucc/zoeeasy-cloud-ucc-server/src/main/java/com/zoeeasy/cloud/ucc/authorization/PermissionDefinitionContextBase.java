package com.zoeeasy.cloud.ucc.authorization;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;

public abstract class PermissionDefinitionContextBase implements IPermissionDefinitionContext {

    protected PermissionMap permissions;

    protected PermissionDefinitionContextBase() {
        permissions = new PermissionMap();
    }

    public Permission createPermission(String code, String name, String description, TenancyHostSide multiTenancySide) {
        if (permissions.containsKey(code)) {
            throw new IllegalArgumentException("There is already a permission with name: " + name);
        }
        Permission permission = new Permission(code, name, description, multiTenancySide);
        permissions.put(permission.getCode(), permission);
        return permission;
    }

    public Permission getPermissionOrNull(String code) {
        return permissions.getOrDefault(code, null);
    }

    public void removePermission(String code) {
        permissions.remove(code);
    }
}