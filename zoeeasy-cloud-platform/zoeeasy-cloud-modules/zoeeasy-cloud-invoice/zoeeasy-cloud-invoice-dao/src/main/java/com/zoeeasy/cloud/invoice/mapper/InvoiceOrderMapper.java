package com.zoeeasy.cloud.invoice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.invoice.domain.InvoiceOrderEntity;
import org.springframework.stereotype.Repository;

/**
 * 发票订单表(InvoiceOrder)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-20 10:21:30
 */
@Repository("invoiceOrderMapper")
public interface InvoiceOrderMapper extends BaseMapper<InvoiceOrderEntity> {
}