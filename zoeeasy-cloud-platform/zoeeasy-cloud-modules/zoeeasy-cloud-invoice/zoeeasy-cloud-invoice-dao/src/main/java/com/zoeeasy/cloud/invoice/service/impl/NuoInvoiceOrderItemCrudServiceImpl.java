package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.NuoInvoiceOrderItemEntity;
import com.zoeeasy.cloud.invoice.mapper.NuoInvoiceOrderItemMapper;
import com.zoeeasy.cloud.invoice.service.NuoInvoiceOrderItemCrudService;
import org.springframework.stereotype.Service;

/**
 * 诺诺发票订单明细(NuoInvoiceOrderItem)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Service("nuoInvoiceOrderItemCrudService")
public class NuoInvoiceOrderItemCrudServiceImpl extends CrudServiceImpl<NuoInvoiceOrderItemMapper, NuoInvoiceOrderItemEntity> implements NuoInvoiceOrderItemCrudService {
}