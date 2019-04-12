package com.zoeeasy.cloud.ucc.authorization;

import com.scapegoat.infrastructure.core.multitenancy.TenancyHostSide;

import java.util.List;

/**
 * 权限管理类
 *
 * @author walkman
 */
public interface IPermissionManager {

    /**
     * 根据代码获取权限
     *
     * @param code code
     * @return
     */
    Permission getPermission(String code) throws Exception;

    /**
     * 根据代码获取权限
     *
     * @param code
     * @return
     */
    Permission getPermissionOrNull(String code);

    /**
     * 获取所有权限
     *
     * @param tenancyFilter
     * @return
     */
    List<Permission> getAllPermissions(boolean tenancyFilter);

    /**
     * @param multiTenancySides
     * @return
     */
    List<Permission> getAllPermissions(TenancyHostSide multiTenancySides);
}
