package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.RolePermissionEntity;
import com.zoeeasy.cloud.ucc.mapper.RolePermissionMapper;
import com.zoeeasy.cloud.ucc.service.RolePermissionCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("rolePermissionCrudService")
public class RolePermissionCrudServiceImpl extends CrudServiceImpl<RolePermissionMapper, RolePermissionEntity>
        implements RolePermissionCrudService {

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    @Override
    public List<RolePermissionEntity> findByRoleId(Long roleId) {
        EntityWrapper<RolePermissionEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过权限查找
     *
     * @param permissionId
     * @return
     */
    @Override
    public List<RolePermissionEntity> findByPermissionId(Long permissionId) {
        EntityWrapper<RolePermissionEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("permissionId", permissionId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过权限ID删除
     *
     * @param permissionId
     * @return
     */
    @Override
    public Integer deleteByPermissionId(Long permissionId) {
        EntityWrapper<RolePermissionEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("permissionId", permissionId);
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
        EntityWrapper<RolePermissionEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过角色权限删除
     *
     * @param roleId
     * @param permissionIdList
     * @return
     */
    @Override
    public Integer deleteByRolePermissionList(Long roleId, List<Long> permissionIdList) {
        EntityWrapper<RolePermissionEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        entityWrapper.in("permissionId", permissionIdList);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 无租户条件查询
     *
     * @param ew
     * @return
     */
    @Override
    public List<RolePermissionEntity> findTenancyless(EntityWrapper<RolePermissionEntity> ew) {
        return this.baseMapper.findTenancyless(ew);
    }

}