package com.zhuyitech.parking.ucc.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserAssetLog;
import com.zhuyitech.parking.ucc.mapper.UserAssetLogMapper;
import com.zhuyitech.parking.ucc.service.UserAssetLogCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @date 2017-11-21
 */
@Service("userAssetLogCrudService")
public class UserAssetLogCrudServiceImpl extends CrudServiceImpl<UserAssetLogMapper, UserAssetLog> implements UserAssetLogCrudService {

    /**
     * @param transactionNo
     * @return
     */
    @Override
    public UserAssetLog findByTransactionNo(String transactionNo) {
        UserAssetLog userAsset = new UserAssetLog();
        userAsset.setTransactionNo(transactionNo);
        return baseMapper.selectOne(userAsset);
    }

    /**
     * @param bizNo
     * @return
     */
    @Override
    public UserAssetLog findByBizNo(String bizNo) {
        UserAssetLog userAsset = new UserAssetLog();
        userAsset.setBizNo(bizNo);
        return baseMapper.selectOne(userAsset);
    }
}
