package com.zoeeasy.cloud.ucc.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.UserRoleEntity;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface UserRoleCrudService extends CrudService<UserRoleEntity> {

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    List<UserRoleEntity> findByUserId(Long userId);

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    List<UserRoleEntity> findByRoleId(Long roleId);

    /**
     * 通过用户ID删除
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 通过角色ID删除
     *
     * @param roleId
     * @return
     */
    Integer deleteByRoleId(Long roleId);

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    List<UserRoleEntity> findByHostUserId(Long userId);

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    List<UserRoleEntity> findByTenancyUserId(Long tenantId, Long userId);

    /**
     * 无租户条件查询
     *
     * @param ew
     * @return
     */
    List<UserRoleEntity> findUserRoleListTenancyLess(EntityWrapper<UserRoleEntity> ew);

}