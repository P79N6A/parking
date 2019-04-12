package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserPayOrder;
import com.zhuyitech.parking.ucc.mapper.UserPayOrderMapper;
import com.zhuyitech.parking.ucc.service.UserPayOrderCrudService;
import com.zoeeasy.cloud.core.enums.PayWayEnum;
import org.springframework.stereotype.Service;


@Service("userPayOrderCrudService")
public class UserPayOrderCrudServiceImpl extends ServiceImpl<UserPayOrderMapper, UserPayOrder> implements UserPayOrderCrudService {

    /**
     * @param orderNo
     * @return
     */
    @Override
    public UserPayOrder findByOrderNo(String orderNo) {
        UserPayOrder userPayOrder = new UserPayOrder();
        userPayOrder.setOrderNo(orderNo);
        return baseMapper.selectOne(userPayOrder);
    }

    /**
     * @param orderNo
     * @return
     */
    @Override
    public UserPayOrder findAliPayByOrderNo(String orderNo) {
        UserPayOrder userPayOrder = new UserPayOrder();
        userPayOrder.setTransactionNo(orderNo);
        userPayOrder.setPayWay(PayWayEnum.ALIPAY.getValue());
        return baseMapper.selectOne(userPayOrder);
    }

    /**
     * @param orderNo
     * @return
     */
    @Override
    public UserPayOrder findWeichatByOrderNo(String orderNo) {
        UserPayOrder userPayOrder = new UserPayOrder();
        userPayOrder.setTransactionNo(orderNo);
        userPayOrder.setPayWay(PayWayEnum.WEIXINPAY.getValue());
        return baseMapper.selectOne(userPayOrder);
    }
}
