package com.zoeeasy.cloud.invoice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.invoice.domain.InvoiceApplyDetailEntity;
import org.springframework.stereotype.Repository;

/**
 * 发票申请详情表(InvoiceApplyDetail)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Repository("invoiceApplyDetailMapper")
public interface InvoiceApplyDetailMapper extends BaseMapper<InvoiceApplyDetailEntity> {
}