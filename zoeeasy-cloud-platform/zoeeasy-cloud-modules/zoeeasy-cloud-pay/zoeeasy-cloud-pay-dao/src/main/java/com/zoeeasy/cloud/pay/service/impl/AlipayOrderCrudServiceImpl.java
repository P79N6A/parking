package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.AlipayOrderEntity;
import com.zoeeasy.cloud.pay.mapper.AlipayOrderMapper;
import com.zoeeasy.cloud.pay.service.AlipayOrderCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("alipayOrderCrudService")
public class AlipayOrderCrudServiceImpl extends ServiceImpl<AlipayOrderMapper, AlipayOrderEntity> implements AlipayOrderCrudService {

    /**
     * @param orderNo
     * @return
     */
    @Override
    public AlipayOrderEntity findByOrderNo(String orderNo) {
        AlipayOrderEntity alipayOrderEntity = new AlipayOrderEntity();
        alipayOrderEntity.setTradeNo(orderNo);
        return baseMapper.selectOne(alipayOrderEntity);
    }

    /**
     * @param outOrderNo
     * @return
     */
    @Override
    public AlipayOrderEntity findByOutOrderNo(String outOrderNo) {
        AlipayOrderEntity alipayOrderEntity = new AlipayOrderEntity();
        alipayOrderEntity.setOutTradeNo(outOrderNo);
        return baseMapper.selectOne(alipayOrderEntity);
    }

    /**
     * 添加支付宝订单
     *
     * @return
     */
    @Override
    public Integer add(AlipayOrderEntity alipayOrderEntity) {
        return baseMapper.add(alipayOrderEntity);
    }

    /**
     * 获取支付宝订单
     *
     * @return
     */
    @Override
    public AlipayOrderEntity get(Wrapper<AlipayOrderEntity> wrapper) {
        return baseMapper.get(wrapper);
    }

    /**
     * 根据outOrderNo更新
     *
     * @return
     */
    @Override
    public Integer updateByOutOrderNo(AlipayOrderEntity alipayOrderEntity) {
        return baseMapper.updateByOutOrderNo(alipayOrderEntity);
    }
}