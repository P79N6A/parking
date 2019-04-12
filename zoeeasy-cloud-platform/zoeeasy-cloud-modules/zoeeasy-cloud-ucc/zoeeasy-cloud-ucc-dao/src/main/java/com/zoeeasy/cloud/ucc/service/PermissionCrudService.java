package com.zoeeasy.cloud.ucc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.PermissionEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface PermissionCrudService extends CrudService<PermissionEntity> {

    /**
     * 获取所有静态权限
     *
     * @return
     */
    List<PermissionEntity> getAllStaticPermission();

    /**
     * 获取所有宿主端静态权限
     *
     * @return
     */
    List<PermissionEntity> getAllHostSideStaticPermission();

    /**
     * 获取所有租户端静态权限
     *
     * @return
     */
    List<PermissionEntity> getAllTenancySideStaticPermission();

    /**
     * 查询宿主端静态权限
     *
     * @return
     */
    List<PermissionEntity> findHostSideStaticPermission(EntityWrapper<PermissionEntity> ew);

    /**
     * 查询租户端静态权限
     *
     * @return
     */
    List<PermissionEntity> findTenancySideStaticPermission(EntityWrapper<PermissionEntity> ew);
}
