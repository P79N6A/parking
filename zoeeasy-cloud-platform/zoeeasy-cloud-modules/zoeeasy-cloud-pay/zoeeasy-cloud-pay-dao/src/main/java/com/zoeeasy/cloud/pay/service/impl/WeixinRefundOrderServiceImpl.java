package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.WeixinRefundOrderEntity;
import com.zoeeasy.cloud.pay.mapper.WeixinRefundOrderMapper;
import com.zoeeasy.cloud.pay.service.WeixinRefundOrderService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("weixinRefundOrderService")
public class WeixinRefundOrderServiceImpl extends ServiceImpl<WeixinRefundOrderMapper, WeixinRefundOrderEntity> implements WeixinRefundOrderService {

}