package com.zhuyitech.parking.ucc.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.ucc.domain.UserRecharge;
import com.zhuyitech.parking.ucc.mapper.UserRechargeMapper;
import com.zhuyitech.parking.ucc.service.UserRechargeCrudService;
import org.springframework.stereotype.Service;


@Service("userRechargeCrudService")
public class UserRechargeCrudServiceImpl extends ServiceImpl<UserRechargeMapper, UserRecharge> implements UserRechargeCrudService {

    /**
     * 通过订单号查找
     *
     * @param orderNo
     * @return
     */
    @Override
    public UserRecharge findByOrderNo(String orderNo) {
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setOrderNo(orderNo);
        return baseMapper.selectOne(userRecharge);
    }

    /**
     * 通过支付订单号 支付方式查找
     *
     * @param payOrderNo
     * @param payType
     * @return
     */
    @Override
    public UserRecharge findByPayOrderNo(String payOrderNo, Integer payType) {
        UserRecharge userRecharge = new UserRecharge();
        userRecharge.setPayOrderNo(payOrderNo);
        userRecharge.setRechargeType(payType);
        return baseMapper.selectOne(userRecharge);
    }
}