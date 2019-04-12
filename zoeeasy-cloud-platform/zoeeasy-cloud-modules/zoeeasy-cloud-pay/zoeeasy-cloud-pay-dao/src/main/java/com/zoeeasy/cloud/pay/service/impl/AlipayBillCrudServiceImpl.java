package com.zoeeasy.cloud.pay.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.pay.domain.AlipayBillEntity;
import com.zoeeasy.cloud.pay.mapper.AlipayBillMapper;
import com.zoeeasy.cloud.pay.service.AlipayBillCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 * @since 2018-09-12
 */
@Service("alipayBillCrudService")
public class AlipayBillCrudServiceImpl extends ServiceImpl<AlipayBillMapper, AlipayBillEntity> implements AlipayBillCrudService {

}