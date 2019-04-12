package com.zoeeasy.cloud.invoice.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.invoice.domain.NuoInvoiceOrderItemEntity;
import org.springframework.stereotype.Repository;

/**
 * 诺诺发票订单明细(NuoInvoiceOrderItem)表数据库访问层
 *
 * @author AkeemSuper
 * @since 2019-02-19 15:30:54
 */
@Repository("nuoInvoiceOrderItemMapper")
public interface NuoInvoiceOrderItemMapper extends BaseMapper<NuoInvoiceOrderItemEntity> {
}