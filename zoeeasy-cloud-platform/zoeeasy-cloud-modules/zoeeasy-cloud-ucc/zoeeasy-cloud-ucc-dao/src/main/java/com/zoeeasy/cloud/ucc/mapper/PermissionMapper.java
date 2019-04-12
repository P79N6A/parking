package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.ucc.domain.PermissionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @since 2018-10-21
 */
@Repository("permissionMapper")
public interface PermissionMapper extends BaseMapper<PermissionEntity> {

    /**
     * 查询所有静态权限
     *
     * @return
     */
    @SqlParser(filter = true)
    List<PermissionEntity> getAllStaticPermission();

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<PermissionEntity> getAllHostSideStaticPermission();

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<PermissionEntity> getAllTenancySideStaticPermission();

    /**
     * 查询宿主端静态权限
     *
     * @return
     */
    @SqlParser(filter = true)
    List<PermissionEntity> findHostSideStaticPermission(@Param("ew") Wrapper<PermissionEntity> ew);

    /**
     * 查询租户端静态权限
     *
     * @return
     */
    @SqlParser(filter = true)
    List<PermissionEntity> findTenancySideStaticPermission(@Param("ew") Wrapper<PermissionEntity> ew);

}