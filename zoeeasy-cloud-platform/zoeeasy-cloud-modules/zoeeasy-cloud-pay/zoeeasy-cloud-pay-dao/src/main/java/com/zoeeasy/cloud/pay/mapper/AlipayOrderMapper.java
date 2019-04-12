package com.zoeeasy.cloud.pay.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.zoeeasy.cloud.pay.domain.AlipayOrderEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zwq
 * @since 2018-01-02
 */
@Repository
public interface AlipayOrderMapper extends BaseMapper<AlipayOrderEntity> {

    /**
     * 添加支付宝订单
     *
     * @param alipayOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer add(AlipayOrderEntity alipayOrderEntity);

    /**
     * 获取支付宝订单
     *
     * @param wrapper
     * @return
     */
    @SqlParser(filter = true)
    AlipayOrderEntity get(@Param("ew") Wrapper<AlipayOrderEntity> wrapper);

    /**
     * 根据outOrderNo更新
     *
     * @param alipayOrderEntity
     * @return
     */
    @SqlParser(filter = true)
    Integer updateByOutOrderNo(AlipayOrderEntity alipayOrderEntity);

}
