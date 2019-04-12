package com.zoeeasy.cloud.ucc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface RoleCrudService extends CrudService<RoleEntity> {

    /**
     * @param code
     * @return
     */
    RoleEntity getByCode(String code);

    /**
     * @param name
     * @return
     */
    RoleEntity getByName(String name);

    /**
     * 查询所有静态角色
     *
     * @return
     */
    List<RoleEntity> getAllStaticRole();

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    List<RoleEntity> getAllTenancySideStaticRole();

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    List<RoleEntity> getAllHostSideStaticRole();

    /**
     * 无租户的条件查询
     *
     * @param ew
     * @return
     */
    List<RoleEntity> findRoleListTenancyLess(EntityWrapper<RoleEntity> ew);

}