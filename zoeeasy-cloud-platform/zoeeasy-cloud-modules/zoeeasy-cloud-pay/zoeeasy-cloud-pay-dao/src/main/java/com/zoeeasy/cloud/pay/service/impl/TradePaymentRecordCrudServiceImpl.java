package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.common.utils.StringUtils;
import com.zoeeasy.cloud.pay.domain.TradePaymentRecordEntity;
import com.zoeeasy.cloud.pay.mapper.TradePaymentRecordMapper;
import com.zoeeasy.cloud.pay.service.TradePaymentRecordCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("tradePaymentRecordCrudService")
public class TradePaymentRecordCrudServiceImpl extends ServiceImpl<TradePaymentRecordMapper, TradePaymentRecordEntity> implements TradePaymentRecordCrudService {

    /**
     * 根据支付订单号获取支付记录
     *
     * @param orderNo
     * @return
     */
    @Override
    public TradePaymentRecordEntity getByOrderNo(String orderNo) {
        return baseMapper.selectByOrderNo(orderNo);
    }

    /**
     * 根据支付用户、支付订单号获取支付记录
     *
     * @param orderNo orderNo
     * @return
     */
    @Override
    public TradePaymentRecordEntity getByCustomerOrderNo(Long customerUserId, String orderNo) {
        return baseMapper.selectByCustomerOrderNo(customerUserId, orderNo);
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
    public Integer updatePayStatus(Long customerUserId, String orderNo, String transactionNo,
                                   Date successPayTime, Integer payStatus) {
        if (StringUtils.isEmpty(orderNo)) {
            return 0;
        }
        TradePaymentRecordEntity tradePaymentRecordEntity = new TradePaymentRecordEntity();
        if (customerUserId != null) {
            tradePaymentRecordEntity.setCustomerUserId(customerUserId);
        }
        tradePaymentRecordEntity.setOrderNo(orderNo);
        if (payStatus != null) {
            tradePaymentRecordEntity.setStatus(payStatus);
        }
        if (successPayTime != null) {
            tradePaymentRecordEntity.setSucceedPayTime(successPayTime);
        }
        if (StringUtils.isNotEmpty(transactionNo)) {
            tradePaymentRecordEntity.setTransactionNo(transactionNo);
        }
        return baseMapper.updateByCustomerOrderNo(tradePaymentRecordEntity);
    }

    /**
     * 添加支付记录
     *
     * @return
     */
    @Override
    public Integer add(TradePaymentRecordEntity tradePaymentRecordEntity) {
        return baseMapper.add(tradePaymentRecordEntity);
    }

    /**
     * 根据bizOrderNo和bizOrdertype获取支付记录
     *
     * @return
     */
    @Override
    public TradePaymentRecordEntity getByBizOrderNo(Wrapper<TradePaymentRecordEntity> wrapper) {
        return baseMapper.getByBizOrderNo(wrapper);
    }
}
