package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserAsset;
import com.zhuyitech.parking.ucc.mapper.UserAssetMapper;
import com.zhuyitech.parking.ucc.service.UserAssetCrudService;
import org.springframework.stereotype.Service;

/**
 * @author walkman
 * @date 2017-11-21
 */
@Service("userAssetCrudService")
public class UserAssetCrudServiceImpl extends CrudServiceImpl<UserAssetMapper, UserAsset> implements UserAssetCrudService {

    /**
     * 通过用户ID查找
     *
     * @param userId
     * @return
     */
    @Override
    public UserAsset findByUserId(Long userId) {
        UserAsset userAsset = new UserAsset();
        userAsset.setUserId(userId);
        return baseMapper.selectOne(userAsset);
    }
}