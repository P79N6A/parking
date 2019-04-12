package com.zoeeasy.cloud.invoice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.invoice.domain.InvoiceOrderDetailEntity;
import org.springframework.stereotype.Repository;

/**
 * 发票订单详情表(InvoiceOrderDetail)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Repository("invoiceOrderDetailMapper")
public interface InvoiceOrderDetailMapper extends BaseMapper<InvoiceOrderDetailEntity> {
}