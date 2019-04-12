package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("roleMapper")
public interface RoleMapper extends BaseMapper<RoleEntity> {

    /**
     * 查询所有静态角色
     *
     * @return
     */
    @SqlParser(filter = true)
    List<RoleEntity> getAllStaticRole();

    /**
     * 获取所有宿主端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<RoleEntity> getAllHostSideStaticRole();

    /**
     * 获取所有租户端静态菜单
     *
     * @return
     */
    @SqlParser(filter = true)
    List<RoleEntity> getAllTenancySideStaticRole();

    /**
     * 无租户的条件查询
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    List<RoleEntity> findRoleListTenancyLess(@Param("ew") EntityWrapper<RoleEntity> ew);

}