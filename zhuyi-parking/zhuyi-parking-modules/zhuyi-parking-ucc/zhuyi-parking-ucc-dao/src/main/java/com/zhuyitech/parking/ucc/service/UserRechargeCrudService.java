package com.zhuyitech.parking.ucc.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.ucc.domain.UserRecharge;

public interface UserRechargeCrudService extends CrudService<UserRecharge> {

    /**
     * 通过订单号查找
     *
     * @param orderNo
     * @return
     */
    UserRecharge findByOrderNo(String orderNo);

    /**
     * 通过支付订单号 支付方式查找
     *
     * @param payOrderNo
     * @param payType
     * @return
     */
    UserRecharge findByPayOrderNo(String payOrderNo, Integer payType);
}
