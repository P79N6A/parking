package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.mapper.RoleMapper;
import com.zoeeasy.cloud.ucc.service.RoleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("roleCrudService")
public class RoleCrudServiceImpl extends CrudServiceImpl<RoleMapper, RoleEntity>
        implements RoleCrudService {

    /**
     * @param code
     * @return
     */
    @Override
    public RoleEntity getByCode(String code) {
        RoleEntity entity = new RoleEntity();
        entity.setCode(code);
        return baseMapper.selectOne(entity);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public RoleEntity getByName(String name) {
        RoleEntity entity = new RoleEntity();
        entity.setName(name);
        return baseMapper.selectOne(entity);
    }

    /**
     * 查询所有静态角色
     *
     * @return
     */
    @Override
    public List<RoleEntity> getAllStaticRole() {
        return baseMapper.getAllStaticRole();
    }

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @Override
    public List<RoleEntity> getAllTenancySideStaticRole() {
        return baseMapper.getAllTenancySideStaticRole();
    }

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @Override
    public List<RoleEntity> getAllHostSideStaticRole() {
        return baseMapper.getAllHostSideStaticRole();
    }

    /**
     * 无租户的条件查询
     *
     * @param ew
     * @return
     */
    @Override
    public List<RoleEntity> findRoleListTenancyLess(EntityWrapper<RoleEntity> ew) {
        return baseMapper.findRoleListTenancyLess(ew);
    }
}