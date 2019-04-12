package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zoeeasy.cloud.ucc.domain.RolePermissionEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Repository("rolePermissionMapper")
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

    /**
     * 无租户的条件查询
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    List<RolePermissionEntity> findTenancyless(@Param("ew") EntityWrapper<RolePermissionEntity> ew);

}