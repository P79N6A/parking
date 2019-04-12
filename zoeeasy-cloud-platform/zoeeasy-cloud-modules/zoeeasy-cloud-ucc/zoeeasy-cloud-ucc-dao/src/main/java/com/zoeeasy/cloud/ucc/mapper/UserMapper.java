package com.zoeeasy.cloud.ucc.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @since 2017-11-21
 */
@Repository("userMapper")
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 通过租户和账号查找租户用户
     *
     * @param tenantId tenantId
     * @param account  account
     * @return UserEntity
     */
    @SqlParser(filter = true)
    UserEntity findTenantUserByAccount(@Param("tenantId") Long tenantId, @Param("account") String account);

    /**
     * 通过账号查找用户
     *
     * @param account account
     * @return UserEntity
     */
    @SqlParser(filter = true)
    UserEntity findUserByAccount(@Param("account") String account);

    /**
     * 通过userid获取用户信息
     *
     * @param userId
     * @return
     */
    @SqlParser(filter = true)
    UserEntity findByUserId(@Param("userId") Long userId);

    /**
     * 更新密码
     *
     * @param tenantId
     * @param userId
     * @param password
     * @param salt
     * @return
     */
    @SqlParser(filter = true)
    Integer updatePassword(@Param("tenantId") Long tenantId, @Param("userId") Long userId, @Param("password") String password, @Param("salt") String salt);
}