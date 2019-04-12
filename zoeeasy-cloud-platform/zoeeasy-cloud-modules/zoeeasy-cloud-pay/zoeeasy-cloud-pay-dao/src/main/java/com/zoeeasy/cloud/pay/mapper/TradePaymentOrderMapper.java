package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.pay.domain.TradePaymentOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author walkman
 * @since 2018-03-22
 */
@Repository
public interface TradePaymentOrderMapper extends BaseMapper<TradePaymentOrderEntity> {

    /**
     * 添加支付订单
     *
     * @param tradePaymentOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(TradePaymentOrderEntity tradePaymentOrderEntity);

    /**
     * 根据会员ID、支付业务类型查找
     *
     * @param customerUserId
     * @param bizOrderType
     * @param bizOrderNo
     * @return
     */
    @SqlParser(filter = true)
    TradePaymentOrderEntity findByCustomerBizOrderNo(@Param("customerUserId") Long customerUserId,
                                                     @Param("bizOrderType") Integer bizOrderType,
                                                     @Param("bizOrderNo") String bizOrderNo);

    /**
     * 根据停车记录号查找
     *
     * @return
     */
    @SqlParser(filter = true)
    TradePaymentOrderEntity findByCustomerOrderNo(@Param("customerUserId") Long customerUserId,
                                                  @Param("orderNo") String orderNo);

    /**
     * 更新支付订单
     *
     * @param tradePaymentOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateByCustomerBizOrderNo(TradePaymentOrderEntity tradePaymentOrderEntity);

    /**
     * 根据orderNo更新支付订单
     *
     * @param tradePaymentOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateByCustomerOrderNo(TradePaymentOrderEntity tradePaymentOrderEntity);
}
