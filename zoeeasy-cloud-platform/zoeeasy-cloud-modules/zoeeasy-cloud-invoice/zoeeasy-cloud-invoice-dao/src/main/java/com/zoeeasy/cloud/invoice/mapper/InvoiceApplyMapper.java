package com.zoeeasy.cloud.invoice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.invoice.domain.InvoiceApplyEntity;
import org.springframework.stereotype.Repository;

/**
 * 发票申请表(InvoiceApply)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:21:30
 */
@Repository("invoiceApplyMapper")
public interface InvoiceApplyMapper extends BaseMapper<InvoiceApplyEntity> {
}