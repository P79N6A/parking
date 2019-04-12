package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserAuthApply;

/**
 * @author walkman
 * @date 2017-11-21
 */
public interface UserAuthApplyCrudService extends CrudService<UserAuthApply> {

    /**
     * 通过用户ID查找
     *
     * @param userId
     * @return
     */
    UserAuthApply findByUserId(Long userId);
}
