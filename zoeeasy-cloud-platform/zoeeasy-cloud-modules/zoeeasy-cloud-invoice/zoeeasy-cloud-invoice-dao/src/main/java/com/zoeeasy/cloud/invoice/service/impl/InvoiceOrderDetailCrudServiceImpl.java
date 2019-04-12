package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.InvoiceOrderDetailEntity;
import com.zoeeasy.cloud.invoice.mapper.InvoiceOrderDetailMapper;
import com.zoeeasy.cloud.invoice.service.InvoiceOrderDetailCrudService;
import org.springframework.stereotype.Service;

/**
 * 发票订单详情表(InvoiceOrderDetail)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Service("invoiceOrderDetailCrudService")
public class InvoiceOrderDetailCrudServiceImpl extends CrudServiceImpl<InvoiceOrderDetailMapper, InvoiceOrderDetailEntity> implements InvoiceOrderDetailCrudService {
}