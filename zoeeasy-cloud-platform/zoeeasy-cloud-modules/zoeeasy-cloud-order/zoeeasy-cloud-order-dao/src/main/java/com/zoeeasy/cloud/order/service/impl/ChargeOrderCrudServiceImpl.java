package com.zoeeasy.cloud.order.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.order.domain.ChargeOrderEntity;
import com.zoeeasy.cloud.order.mapper.ChargeOrderMapper;
import com.zoeeasy.cloud.order.service.ChargeOrderCrudService;
import org.springframework.stereotype.Service;


/**
 * @author walkman
 * @since 2019-01-16
 */
@Service("chargeOrderCrudService")
public class ChargeOrderCrudServiceImpl extends CrudServiceImpl<ChargeOrderMapper, ChargeOrderEntity> implements ChargeOrderCrudService {

}