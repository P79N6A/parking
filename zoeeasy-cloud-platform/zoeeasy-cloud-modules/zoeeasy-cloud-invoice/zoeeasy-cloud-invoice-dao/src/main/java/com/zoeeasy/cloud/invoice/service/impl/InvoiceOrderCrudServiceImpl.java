package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.InvoiceOrderEntity;
import com.zoeeasy.cloud.invoice.mapper.InvoiceOrderMapper;
import com.zoeeasy.cloud.invoice.service.InvoiceOrderCrudService;
import org.springframework.stereotype.Service;

/**
 * 发票订单表(InvoiceOrder)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:21:30
 */
@Service("invoiceOrderCrudService")
public class InvoiceOrderCrudServiceImpl extends CrudServiceImpl<InvoiceOrderMapper, InvoiceOrderEntity> implements InvoiceOrderCrudService {
}