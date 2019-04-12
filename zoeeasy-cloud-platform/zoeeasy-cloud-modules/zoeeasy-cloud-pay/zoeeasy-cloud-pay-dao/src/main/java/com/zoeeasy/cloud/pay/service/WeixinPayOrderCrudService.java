package com.zoeeasy.cloud.pay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.WeixinPayOrderEntity;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface WeixinPayOrderCrudService extends CrudService<WeixinPayOrderEntity> {

    /**
     * @param outTradeNo
     * @return
     */
    WeixinPayOrderEntity findByOutTradeNo(String outTradeNo);

    /**
     * 添加微信订单
     *
     */
    Integer add(WeixinPayOrderEntity weixinPayOrderEntity);

    /**
     * 获取微信订单
     *
     * @param wrapper
     * @return
     */
    WeixinPayOrderEntity get(Wrapper<WeixinPayOrderEntity> wrapper);

    /**
     * 根据outOrderNo更新
     *
     * @param alipayOrderEntity
     * @return
     */
    Integer updateByOutOrderNo(WeixinPayOrderEntity alipayOrderEntity);

}