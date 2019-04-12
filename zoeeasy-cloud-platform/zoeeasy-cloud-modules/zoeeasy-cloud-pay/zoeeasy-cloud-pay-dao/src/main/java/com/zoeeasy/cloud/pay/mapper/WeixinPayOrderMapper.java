package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pay.domain.WeixinPayOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WeixinPayOrderMapper extends BaseMapper<WeixinPayOrderEntity> {

    /**
     * 添加支付宝订单
     *
     * @param weixinPayOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(WeixinPayOrderEntity weixinPayOrderEntity);

    /**
     * 获取支付宝订单
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    WeixinPayOrderEntity get(@Param("ew") Wrapper<WeixinPayOrderEntity> wrapper);

    /**
     * 根据outOrderNo更新
     *
     * @param weixinPayOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateByOutOrderNo(WeixinPayOrderEntity weixinPayOrderEntity);

}