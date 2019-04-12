package com.zoeeasy.cloud.ucc.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.PermissionEntity;
import com.zoeeasy.cloud.ucc.mapper.PermissionMapper;
import com.zoeeasy.cloud.ucc.service.PermissionCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("permissionCrudService")
public class PermissionCrudServiceImpl extends CrudServiceImpl<PermissionMapper, PermissionEntity>
        implements PermissionCrudService {

    /**
     * 获取所有静态权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> getAllStaticPermission() {
        return this.baseMapper.getAllStaticPermission();
    }

    /**
     * 获取所有宿主端静态权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> getAllHostSideStaticPermission() {
        return this.baseMapper.getAllHostSideStaticPermission();
    }

    /**
     * 获取所有租户端静态权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> getAllTenancySideStaticPermission() {
        return this.baseMapper.getAllTenancySideStaticPermission();
    }

    /**
     * 查询宿主端静态权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> findHostSideStaticPermission(EntityWrapper<PermissionEntity> ew) {
        return this.baseMapper.findHostSideStaticPermission(ew);
    }

    /**
     * 查询租户端静态权限
     *
     * @return
     */
    @Override
    public List<PermissionEntity> findTenancySideStaticPermission(EntityWrapper<PermissionEntity> ew) {
        return this.baseMapper.findTenancySideStaticPermission(ew);
    }

}
