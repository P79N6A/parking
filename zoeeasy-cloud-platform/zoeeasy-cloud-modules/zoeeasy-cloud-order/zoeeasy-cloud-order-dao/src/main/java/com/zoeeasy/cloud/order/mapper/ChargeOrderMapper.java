package com.zoeeasy.cloud.order.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.order.domain.ChargeOrderEntity;
import org.springframework.stereotype.Repository;


/**
 * @author walkman
 * @since 2019-01-16
 */
@Repository("chargeOrderMapper")
public interface ChargeOrderMapper extends BaseMapper<ChargeOrderEntity> {

}