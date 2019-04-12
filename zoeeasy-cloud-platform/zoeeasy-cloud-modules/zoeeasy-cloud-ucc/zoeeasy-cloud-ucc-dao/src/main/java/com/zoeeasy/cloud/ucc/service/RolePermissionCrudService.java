package com.zoeeasy.cloud.ucc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.RolePermissionEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface RolePermissionCrudService extends CrudService<RolePermissionEntity> {

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    List<RolePermissionEntity> findByRoleId(Long roleId);

    /**
     * 通过权限查找
     *
     * @param permissionId
     * @return
     */
    List<RolePermissionEntity> findByPermissionId(Long permissionId);

    /**
     * 通过权限ID删除
     *
     * @param permissionId
     * @return
     */
    Integer deleteByPermissionId(Long permissionId);

    /**
     * 通过角色ID删除
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 通过角色权限删除
     *
     * @param roleId
     * @param permissionIdList
     * @return
     */
    Integer deleteByRolePermissionList(Long roleId, List<Long> permissionIdList);

    /**
     * 无租户条件查询
     *
     * @param ew
     * @return
     */
    List<RolePermissionEntity> findTenancyless(EntityWrapper<RolePermissionEntity> ew);

}