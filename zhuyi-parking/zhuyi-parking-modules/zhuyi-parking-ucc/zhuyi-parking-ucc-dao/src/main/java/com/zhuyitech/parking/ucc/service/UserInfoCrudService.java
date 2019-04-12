package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserInfo;

/**
 * @author walkman
 * @date 2017-11-21
 */
public interface UserInfoCrudService extends CrudService<UserInfo> {

    /**
     * 通过用户ID查找
     *
     * @param userId
     * @return
     */
    UserInfo findByUserId(Long userId);

    /**
     * 通过微信OpenId查找
     *
     * @param openId
     * @return
     */
    UserInfo findByOpenId(String openId);

    /**
     * 通过微信UnionID查找
     *
     * @param unionId
     * @return
     */
    UserInfo findByUnionId(String unionId);

    /**
     * 通过微信aliUserId查找
     *
     * @param aliUserId
     * @return
     */
    UserInfo findByAliUserId(String aliUserId);
}