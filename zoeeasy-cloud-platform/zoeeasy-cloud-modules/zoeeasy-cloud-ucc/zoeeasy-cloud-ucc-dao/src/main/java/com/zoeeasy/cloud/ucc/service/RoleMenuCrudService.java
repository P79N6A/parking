package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.RoleMenuEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface RoleMenuCrudService extends CrudService<RoleMenuEntity> {

    /**
     * 通过菜单查找
     *
     * @param menuId
     * @return
     */
    List<RoleMenuEntity> findByMenuId(Long menuId);

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    List<RoleMenuEntity> findByRoleId(Long roleId);

    /**
     * 通过角色查找
     *
     * @param roleIdList
     * @return
     */
    List<RoleMenuEntity> findByRoleIdList(List<Long> roleIdList);

    /**
     * 通过角色ID删除
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 通过菜单ID删除
     *
     * @param menuId
     * @return
     */
    Integer deleteByMenuId(Long menuId);

    /**
     * 通过角色菜单删除
     *
     * @param roleId
     * @param menuIdList
     * @return
     */
    Integer deleteByRoleMenuList(Long roleId, List<Long> menuIdList);

}