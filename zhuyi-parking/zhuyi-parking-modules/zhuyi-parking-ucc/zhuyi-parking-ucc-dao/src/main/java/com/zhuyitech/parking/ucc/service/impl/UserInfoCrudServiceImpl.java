package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserInfo;
import com.zhuyitech.parking.ucc.mapper.UserInfoMapper;
import com.zhuyitech.parking.ucc.service.UserInfoCrudService;
import org.springframework.stereotype.Service;


@Service("userInfoCrudService")
public class UserInfoCrudServiceImpl extends CrudServiceImpl<UserInfoMapper, UserInfo> implements UserInfoCrudService {

    /**
     * 通过用户ID查找
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfo findByUserId(Long userId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        return baseMapper.selectOne(userInfo);
    }

    /**
     * 通过微信OpenId查找
     *
     * @param openId
     * @return
     */
    @Override
    public UserInfo findByOpenId(String openId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setWxOpenId(openId);
        userInfo.setWxAuthorized(Boolean.TRUE);
        return baseMapper.selectOne(userInfo);
    }

    /**
     * 通过微信UnionID查找
     *
     * @param unionId
     * @return
     */
    @Override
    public UserInfo findByUnionId(String unionId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setWxUnionId(unionId);
        userInfo.setWxAuthorized(Boolean.TRUE);
        return baseMapper.selectOne(userInfo);
    }

    @Override
    public UserInfo findByAliUserId(String aliUserId) {
        UserInfo userInfo = new UserInfo();
        userInfo.setAliUserId(aliUserId);
        userInfo.setAliAuthorized(Boolean.TRUE);
        return baseMapper.selectOne(userInfo);
    }
}