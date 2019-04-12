package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.WeixinPayOrderEntity;
import com.zoeeasy.cloud.pay.mapper.WeixinPayOrderMapper;
import com.zoeeasy.cloud.pay.service.WeixinPayOrderCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("weixinPayOrderCrudService")
public class WeixinPayOrderCrudServiceImpl extends ServiceImpl<WeixinPayOrderMapper, WeixinPayOrderEntity> implements WeixinPayOrderCrudService {

    @Override
    public WeixinPayOrderEntity findByOutTradeNo(String outTradeNo) {
        WeixinPayOrderEntity weixinPayOrderEntity = new WeixinPayOrderEntity();
        weixinPayOrderEntity.setOutTradeNo(outTradeNo);
        return baseMapper.selectOne(weixinPayOrderEntity);
    }

    /**
     * 添加微信订单
     *
     * @return
     */
    @Override
    public Integer add(WeixinPayOrderEntity weixinPayOrderEntity) {
        return baseMapper.add(weixinPayOrderEntity);
    }

    /**
     * 获取微信订单
     *
     * @return
     */
    @Override
    public WeixinPayOrderEntity get(Wrapper<WeixinPayOrderEntity> wrapper) {
        return baseMapper.get(wrapper);
    }

    /**
     * 根据outOrderNo更新
     *
     * @return
     */
    @Override
    public Integer updateByOutOrderNo(WeixinPayOrderEntity alipayOrderEntity) {
        return baseMapper.updateByOutOrderNo(alipayOrderEntity);
    }
}