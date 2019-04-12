package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserAssetLog;

/**
 * @author walkman
 * @date 2018-01-11
 */
public interface UserAssetLogCrudService extends CrudService<UserAssetLog> {

    /**
     * @param transactionNo
     * @return
     */
    UserAssetLog findByTransactionNo(String transactionNo);

    /**
     * @param bizNo
     * @return
     */
    UserAssetLog findByBizNo(String bizNo);
}