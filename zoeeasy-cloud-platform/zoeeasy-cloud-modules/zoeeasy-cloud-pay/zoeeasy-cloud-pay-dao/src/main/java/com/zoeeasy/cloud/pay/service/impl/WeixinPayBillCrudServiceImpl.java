package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.WeixinPayBillEntity;
import com.zoeeasy.cloud.pay.mapper.WeixinPayBillMapper;
import com.zoeeasy.cloud.pay.service.WeixinPayBillCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("weixinPayBillCrudService")
public class WeixinPayBillCrudServiceImpl extends ServiceImpl<WeixinPayBillMapper, WeixinPayBillEntity> implements WeixinPayBillCrudService {

}