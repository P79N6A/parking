package com.zoeeasy.cloud.pay.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.TradePaymentOrderEntity;

import java.util.Date;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface TradePaymentOrderCrudService extends CrudService<TradePaymentOrderEntity> {

    /**
     * 添加支付订单
     */
    Integer add(TradePaymentOrderEntity tradePaymentOrderEntity);

    /**
     * 通过会员ID、订单号查找支付订单
     *
     * @param customerUserId customerUserId
     * @param bizOrderType   bizOrderType
     * @param bizOrderNo     bizOrderNo
     * @return
     */
    TradePaymentOrderEntity findByCustomerBizOrderNo(Long customerUserId, Integer bizOrderType, String bizOrderNo);

    /**
     * 通过会员ID、支付订单号查找支付订单
     *
     * @param customerUserId customerUserId
     * @param orderNo        orderNo
     * @return
     */
    TradePaymentOrderEntity findByCustomerOrderNo(Long customerUserId, String orderNo);

    /**
     * 通过业务订单号更新支付订单
     *
     * @param tradePaymentOrderEntity
     * @return
     */
    Integer updateByCustomerBizOrderNo(TradePaymentOrderEntity tradePaymentOrderEntity);

    /**
     * 修改支付订单支付状态
     *
     * @param orderNo        orderNo
     * @param transactionNo  transactionNo
     * @param customerUserId customerUserId
     * @param successPayTime successPayTime
     * @param payStatus      payStatus
     * @return
     */
    Integer updatePayStatus(Long customerUserId, String orderNo, String transactionNo, Date successPayTime, Integer payStatus);

}
