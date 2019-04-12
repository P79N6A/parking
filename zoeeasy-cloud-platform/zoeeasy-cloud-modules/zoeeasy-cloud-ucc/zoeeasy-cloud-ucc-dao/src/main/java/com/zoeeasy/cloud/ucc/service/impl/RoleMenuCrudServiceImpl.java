package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.RoleMenuEntity;
import com.zoeeasy.cloud.ucc.mapper.RoleMenuMapper;
import com.zoeeasy.cloud.ucc.service.RoleMenuCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("roleMenuCrudService")
public class RoleMenuCrudServiceImpl extends CrudServiceImpl<RoleMenuMapper, RoleMenuEntity>
        implements RoleMenuCrudService {

    /**
     * 通过菜单查找
     *
     * @param menuId
     * @return
     */
    @Override
    public List<RoleMenuEntity> findByMenuId(Long menuId) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("menuId", menuId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过角色查找
     *
     * @param roleId
     * @return
     */
    @Override
    public List<RoleMenuEntity> findByRoleId(Long roleId) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过角色查找
     *
     * @param roleIdList
     * @return
     */
    @Override
    public List<RoleMenuEntity> findByRoleIdList(List<Long> roleIdList) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.in("roleId", roleIdList);
        return this.baseMapper.selectList(entityWrapper);
    }

    /**
     * 通过角色ID删除
     *
     * @param roleId
     * @return
     */
    @Override
    public Integer deleteByRoleId(Long roleId) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过菜单ID删除
     *
     * @param menuId
     * @return
     */
    @Override
    public Integer deleteByMenuId(Long menuId) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("menuId", menuId);
        return this.baseMapper.delete(entityWrapper);
    }

    /**
     * 通过角色菜单删除
     *
     * @param roleId
     * @param menuIdList
     * @return
     */
    @Override
    public Integer deleteByRoleMenuList(Long roleId, List<Long> menuIdList) {
        EntityWrapper<RoleMenuEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("roleId", roleId);
        entityWrapper.in("menuId", menuIdList);
        return this.baseMapper.delete(entityWrapper);
    }

}