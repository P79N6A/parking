package com.zoeeasy.cloud.invoice.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.invoice.domain.InvoiceApplyEntity;
import com.zoeeasy.cloud.invoice.mapper.InvoiceApplyMapper;
import com.zoeeasy.cloud.invoice.service.InvoiceApplyCrudService;
import org.springframework.stereotype.Service;

/**
 * 发票申请表(InvoiceApply)表服务实现类
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:21:30
 */
@Service("invoiceApplyCrudService")
public class InvoiceApplyCrudServiceImpl extends CrudServiceImpl<InvoiceApplyMapper, InvoiceApplyEntity> implements InvoiceApplyCrudService {
}