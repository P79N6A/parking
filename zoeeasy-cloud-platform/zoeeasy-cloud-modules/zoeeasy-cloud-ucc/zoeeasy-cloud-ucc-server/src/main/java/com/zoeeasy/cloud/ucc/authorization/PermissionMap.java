package com.zoeeasy.cloud.ucc.authorization;

import java.util.HashMap;

/**
 * 权限字典
 * <p>
 * Used to store and manipulate dictionary of permissions
 *
 * @author walkman
 * *
 */
class PermissionMap extends HashMap<String, Permission> {

    /**
     * Adds all child permissions of current permissions recursively.
     */
    public void AddAllPermissions() {
        for (Permission permission : this.values()) {
            AddPermissionRecursively(permission);
        }
    }

    /**
     * Adds a permission and it's all child permissions to dictionary.
     *
     * @param permission Permission to be added
     */
    private void AddPermissionRecursively(Permission permission) {
        //Prevent multiple adding of same named permission.
        if (get(permission.getCode()) != null) {
            this.put(permission.getCode(), permission);
        }
        //Add child permissions (recursive call)
        for (Permission childPermission : permission.getChildren()) {
            AddPermissionRecursively(childPermission);
        }
    }
}
