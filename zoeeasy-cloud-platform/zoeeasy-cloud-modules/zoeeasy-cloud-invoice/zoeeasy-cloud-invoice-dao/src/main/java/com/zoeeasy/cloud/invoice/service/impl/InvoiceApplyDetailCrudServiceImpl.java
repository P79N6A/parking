package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.InvoiceApplyDetailEntity;
import com.zoeeasy.cloud.invoice.mapper.InvoiceApplyDetailMapper;
import com.zoeeasy.cloud.invoice.service.InvoiceApplyDetailCrudService;
import org.springframework.stereotype.Service;

/**
 * 发票申请详情表(InvoiceApplyDetail)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Service("invoiceApplyDetailCrudService")
public class InvoiceApplyDetailCrudServiceImpl extends CrudServiceImpl<InvoiceApplyDetailMapper, InvoiceApplyDetailEntity> implements InvoiceApplyDetailCrudService {
}