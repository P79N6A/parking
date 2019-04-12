package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.UserRoleEntity;
import com.zoeeasy.cloud.ucc.mapper.UserRoleMapper;
import com.zoeeasy.cloud.ucc.service.UserRoleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("userRoleCrudService")
public class UserRoleCrudServiceImpl extends CrudServiceImpl<UserRoleMapper, UserRoleEntity>
        implements UserRoleCrudService {

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRoleEntity> findByUserId(Long userId) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    @Override
    public List<UserRoleEntity> findByRoleId(Long roleId) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过用户ID删除
     *
     * @param userId
     * @return
     */
    @Override
    public Integer deleteByUserId(Long userId) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("userId", userId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过角色ID删除
     *
     * @param roleId
     * @return
     */
    @Override
    public Integer deleteByRoleId(Long roleId) {
        EntityWrapper<UserRoleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRoleEntity> findByHostUserId(Long userId) {
        return this.baseMapper.findByHostUserId(userId);
    }

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserRoleEntity> findByTenancyUserId(Long tenantId, Long userId) {
        return this.baseMapper.findByTenancyUserId(tenantId, userId);
    }

    /**
     * 无租户条件查询
     *
     * @param ew
     * @return
     */
    @Override
    public List<UserRoleEntity> findUserRoleListTenancyLess(EntityWrapper<UserRoleEntity> ew) {
        return this.baseMapper.findUserRoleListTenancyLess(ew);
    }

}