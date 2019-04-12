package com.zoeeasy.cloud.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.ucc.domain.UserEntity;
import com.zoeeasy.cloud.ucc.mapper.UserMapper;
import com.zoeeasy.cloud.ucc.service.UserCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @since 2018-08-20
 */
@Service("userCrudService")
public class UserCrudServiceImpl extends CrudServiceImpl<UserMapper, UserEntity>
        implements UserCrudService {

    @Override
    public UserEntity getTenantUserByAccount(Long tenantId, String account) {
        return this.baseMapper.findTenantUserByAccount(tenantId, account);
    }

    @Override
    public UserEntity getUserByAccount(String account) {
        return this.baseMapper.findUserByAccount(account);
    }

    /**
     * 通过用户名查找用户
     *
     * @param userName userName
     * @return UserEntity
     */
    @Override
    public UserEntity findByUserName(String userName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        return this.baseMapper.selectOne(userEntity);
    }

    /**
     * 通过userid获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserEntity finByUserId(Long userId) {
        return baseMapper.findByUserId(userId);
    }

    @Override
    public Integer updatePassword(Long tenantId, Long userId, String password, String salt) {
        return this.baseMapper.updatePassword(tenantId, userId, password, salt);
    }
}
