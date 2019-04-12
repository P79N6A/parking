package com.zoeeasy.cloud.pay.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.pay.domain.TradePaymentRecordEntity;

import java.util.Date;

/**
 * @author zwq
 * @since 2018-09-12
 */
public interface TradePaymentRecordCrudService extends CrudService<TradePaymentRecordEntity> {

    /**
     * 添加支付记录
     */
    Integer add(TradePaymentRecordEntity tradePaymentRecordEntity);

    /**
     * 根据支付订单号获取支付记录
     *
     * @param orderNo orderNo
     * @return
     */
    TradePaymentRecordEntity getByOrderNo(String orderNo);

    /**
     * 根据支付用户、支付订单号获取支付记录
     *
     * @param orderNo orderNo
     * @return
     */
    TradePaymentRecordEntity getByCustomerOrderNo(Long customerUserId, String orderNo);

    /**
     * 修改支付订单支付状态
     *
     * @param orderNo        orderNo
     * @param transactionNo  transactionNo
     * @param payedUserId    payedUserId
     * @param successPayTime successPayTime
     * @param payStatus      payStatus
     * @return
     */
    Integer updatePayStatus(Long payedUserId, String orderNo, String transactionNo,
                            Date successPayTime, Integer payStatus);

    /**
     * 根据bizOrderNo获取支付记录
     *
     * @param wrapper wrapper
     * @return
     */
    TradePaymentRecordEntity getByBizOrderNo(Wrapper<TradePaymentRecordEntity> wrapper);
}
