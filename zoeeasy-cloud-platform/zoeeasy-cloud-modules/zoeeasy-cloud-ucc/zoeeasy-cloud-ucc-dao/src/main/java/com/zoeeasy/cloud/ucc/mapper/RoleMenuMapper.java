package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zoeeasy.cloud.ucc.domain.RoleEntity;
import com.zoeeasy.cloud.ucc.domain.RoleMenuEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("roleMenuMapper")
public interface RoleMenuMapper extends BaseMapper<RoleMenuEntity> {

    /**
     * 无租户的条件查询
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    List<RoleEntity> findRoleMenuListTenancyLess(@Param("ew") EntityWrapper<RoleMenuEntity> ew);

}