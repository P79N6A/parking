package com.zoeeasy.cloud.pay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.AlipayOrderEntity;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface AlipayOrderCrudService extends CrudService<AlipayOrderEntity> {

    /**
     * @param orderNo
     * @return
     */
    AlipayOrderEntity findByOrderNo(String orderNo);

    /**
     * @param outOrderNo
     * @return
     */
    AlipayOrderEntity findByOutOrderNo(String outOrderNo);

    /**
     * 添加支付宝订单
     */
    Integer add(AlipayOrderEntity alipayOrderEntity);

    /**
     * 获取支付宝订单
     *
     * @param wrapper
     * @return
     */
    AlipayOrderEntity get(Wrapper<AlipayOrderEntity> wrapper);

    /**
     * 根据outOrderNo更新
     *
     * @param alipayOrderEntity
     * @return
     */
    Integer updateByOutOrderNo(AlipayOrderEntity alipayOrderEntity);

}