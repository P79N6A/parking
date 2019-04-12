package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pay.domain.TradePaymentRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @since 2018-03-22
 */
@Repository
public interface TradePaymentRecordMapper extends BaseMapper<TradePaymentRecordEntity> {

    /**
     * 添加支付记录
     *
     * @param tradePaymentRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(TradePaymentRecordEntity tradePaymentRecordEntity);

    /**
     * 根据orderNo查询支付记录
     *
     * @param orderNo
     * @return
     */
    @SqlParser(filter = true)
    TradePaymentRecordEntity selectByOrderNo(@Param("orderNo") String orderNo);


    /**
     * 根据支付用户ID、订单号获取支付记录
     *
     * @param customerUserId
     * @param orderNo
     * @return
     */
    @SqlParser(filter = true)
    TradePaymentRecordEntity selectByCustomerOrderNo(@Param("customerUserId") Long customerUserId, @Param("orderNo") String orderNo);

    /**
     * 根据orderNo更新支付订单
     *
     * @param tradePaymentRecordEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateByCustomerOrderNo(TradePaymentRecordEntity tradePaymentRecordEntity);

    /**
     * 根据bizOrderNo和bizOrderType查询支付记录
     *
     * @param ew
     * @return
     */
    @SqlParser(filter = true)
    TradePaymentRecordEntity getByBizOrderNo(@Param("ew") Wrapper<TradePaymentRecordEntity> ew);

}