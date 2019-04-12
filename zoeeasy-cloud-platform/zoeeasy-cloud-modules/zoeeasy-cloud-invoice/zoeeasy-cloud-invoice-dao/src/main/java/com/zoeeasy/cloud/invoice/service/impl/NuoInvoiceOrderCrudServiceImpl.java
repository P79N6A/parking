package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.NuoInvoiceOrderEntity;
import com.zoeeasy.cloud.invoice.mapper.NuoInvoiceOrderMapper;
import com.zoeeasy.cloud.invoice.service.NuoInvoiceOrderCrudService;
import org.springframework.stereotype.Service;

/**
 * 诺诺发票订单表(NuoInvoiceOrder)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Service("nuoInvoiceOrderCrudService")
public class NuoInvoiceOrderCrudServiceImpl extends CrudServiceImpl<NuoInvoiceOrderMapper, NuoInvoiceOrderEntity> implements NuoInvoiceOrderCrudService {
}