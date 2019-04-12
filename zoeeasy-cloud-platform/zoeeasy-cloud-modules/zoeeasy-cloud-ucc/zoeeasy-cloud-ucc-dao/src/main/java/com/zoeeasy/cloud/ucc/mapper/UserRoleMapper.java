package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.zoeeasy.cloud.ucc.domain.UserRoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("userRoleMapper")
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @SqlParser(filter = true)
    List<UserRoleEntity> findByHostUserId(@Param("userId") Long userId);

    /**
     * 通过用户查找
     *
     * @param userId
     * @return
     */
    @SqlParser(filter = true)
    List<UserRoleEntity> findByTenancyUserId(@Param("tenantId") Long tenantId, @Param("userId") Long userId);

    /**
     * 无租户条件查询
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    List<UserRoleEntity> findUserRoleListTenancyLess(@Param("ew") EntityWrapper<UserRoleEntity> ew);

}