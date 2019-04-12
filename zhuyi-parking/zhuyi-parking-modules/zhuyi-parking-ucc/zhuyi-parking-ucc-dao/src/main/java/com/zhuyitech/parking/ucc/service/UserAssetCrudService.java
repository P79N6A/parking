package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserAsset;

/**
 * @author walkman
 * @date 2018-01-11
 */
public interface UserAssetCrudService extends CrudService<UserAsset> {

    /**
     * 通过用户ID查找
     *
     * @param userId
     * @return
     */
    UserAsset findByUserId(Long userId);
}
