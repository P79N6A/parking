package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pay.domain.TradePaymentOrderEntity;
import com.zoeeasy.cloud.pay.mapper.TradePaymentOrderMapper;
import com.zoeeasy.cloud.pay.service.TradePaymentOrderCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("tradePaymentOrderCrudService")
public class TradePaymentOrderCrudServiceImpl extends ServiceImpl<TradePaymentOrderMapper, TradePaymentOrderEntity> implements TradePaymentOrderCrudService {

    /**
     * 添加支付订单
     *
     * @return
     */
    @Override
    public Integer add(TradePaymentOrderEntity tradePaymentOrderEntity) {
        return baseMapper.add(tradePaymentOrderEntity);
    }

    /**
     * 通过业务订单号查找支付订单
     *
     * @param customerUserId customerUserId
     * @param bizOrderType   bizOrderType
     * @param bizOrderNo     bizOrderNo
     * @return
     */
    @Override
    public TradePaymentOrderEntity findByCustomerBizOrderNo(Long customerUserId, Integer bizOrderType, String bizOrderNo) {
        return baseMapper.findByCustomerBizOrderNo(customerUserId, bizOrderType, bizOrderNo);
    }

    /**
     * 通过会员ID、支付订单号查找支付订单
     *
     * @param customerUserId customerUserId
     * @param orderNo        orderNo
     * @return
     */
    @Override
    public TradePaymentOrderEntity findByCustomerOrderNo(Long customerUserId, String orderNo) {
        return baseMapper.findByCustomerOrderNo(customerUserId, orderNo);
    }

    /**
     * 修改支付订单支付状态
     *
     * @param transactionNo  transactionNo
     * @param customerUserId payedUserId
     * @param successPayTime successPayTime
     * @param payStatus      payStatus
     * @return
     */
    @Override
    public Integer updatePayStatus(Long customerUserId, String orderNo, String transactionNo, Date successPayTime, Integer payStatus) {
        if (StringUtils.isEmpty(orderNo) || customerUserId == null) {
            return 0;
        }
        TradePaymentOrderEntity tradePaymentOrderEntity = new TradePaymentOrderEntity();
        tradePaymentOrderEntity.setOrderNo(orderNo);
        tradePaymentOrderEntity.setCustomerUserId(customerUserId);
        if (StringUtils.isNotEmpty(transactionNo)) {
            tradePaymentOrderEntity.setTransactionNo(transactionNo);
        }
        if (payStatus != null) {
            tradePaymentOrderEntity.setStatus(payStatus);
        }
        if (successPayTime != null) {
            tradePaymentOrderEntity.setSucceedPayTime(successPayTime);
        }
        return baseMapper.updateByCustomerOrderNo(tradePaymentOrderEntity);
    }

    /**
     * 通过业务订单号更新支付订单
     *
     * @return
     */
    @Override
    public Integer updateByCustomerBizOrderNo(TradePaymentOrderEntity tradePaymentOrderEntity) {
        return baseMapper.updateByCustomerBizOrderNo(tradePaymentOrderEntity);
    }
}
