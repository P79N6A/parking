package com.zoeeasy.cloud.ucc.authorization;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限定义
 *
 * @author walkman
 */
@Getter
@Setter
@NoArgsConstructor
public class Permission {

    /**
     * 父权限
     */
    private Permission parent;

    /**
     * Unique name of the permission.
     * This is the key name to grant permissions.
     */
    private String code;

    /**
     * Display name of the permission.
     * This can be used to show permission to the user.
     */
    private String name;

    /**
     * A brief description for this permission
     */
    private String description;

    /**
     * Which side can use this permission
     */
    private TenancyHostSide multiTenancySide;

    /**
     * List of child permissions. A child permission can be granted only if parent is granted.
     */
    private List<Permission> children;

    /**
     * Creates a new Permission
     *
     * @param name             Unique name of the permission
     * @param name             Display name of the permission
     * @param description      Which side can use this permission
     * @param multiTenancySide Depended feature(s) of this permission
     */
    public Permission(String code, String name, String description, TenancyHostSide multiTenancySide) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.multiTenancySide = multiTenancySide;
        this.children = new ArrayList<>();
    }

    /**
     * 添加子权限
     *
     * @param code
     * @param name
     * @param description
     * @param multiTenancySides
     * @return
     */
    public Permission createChildPermission(String code, String name, String description, TenancyHostSide multiTenancySides) {
        Permission permission = new Permission(code, name, description, multiTenancySides);
        permission.setParent(this);
        this.children.add(permission);
        return permission;
    }

}
