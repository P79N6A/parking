package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;

/**
 * @author walkman
 */
public interface UserPayOrderCrudService extends CrudService<UserPayOrder> {

    /**
     * @param orderNo
     * @return
     */
    UserPayOrder findByOrderNo(String orderNo);

    /**
     * @param orderNo
     * @return
     */
    UserPayOrder findAliPayByOrderNo(String orderNo);

    /**
     * @param orderNo
     * @return
     */
    UserPayOrder findWeichatByOrderNo(String orderNo);
}
