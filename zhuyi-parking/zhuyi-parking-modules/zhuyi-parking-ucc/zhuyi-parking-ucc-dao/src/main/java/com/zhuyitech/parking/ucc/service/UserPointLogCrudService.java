package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserPointLog;


/**
 * @author walkman
 * @date 2018-01-11
 */
public interface UserPointLogCrudService extends CrudService<UserPointLog> {

    /**
     * @param transactionNo
     * @return
     */
    UserPointLog findByTransactionNo(String transactionNo);
}
