package com.zoeeasy.cloud.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.ucc.domain.UserEntity;

/**
 * @author walkman
 * @since 2018-08-20
 */
public interface UserCrudService extends CrudService<UserEntity> {

    /**
     * 通过租户和账号查找租户用户
     *
     * @param tenantId tenantId
     * @param account  account
     * @return UserEntity
     */
    UserEntity getTenantUserByAccount(Long tenantId, String account);

    /**
     * 通过账号查找用户
     *
     * @param account account
     * @return UserEntity
     */
    UserEntity getUserByAccount(String account);

    /**
     * 通过用户名查找用户
     *
     * @param userName userName
     * @return UserEntity
     */
    UserEntity findByUserName(String userName);

    /**
     * -通过userid获取用户信息
     *
     * @param userId
     * @return
     */
    UserEntity finByUserId(Long userId);

    /**
     * 更新租户管理员密码
     *
     * @param tenantId
     * @param userId
     * @param password
     * @param salt
     * @return
     */
    Integer updatePassword(Long tenantId, Long userId, String password, String salt);
}
