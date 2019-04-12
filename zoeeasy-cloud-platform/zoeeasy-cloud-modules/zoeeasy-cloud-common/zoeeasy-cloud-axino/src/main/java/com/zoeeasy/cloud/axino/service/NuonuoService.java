package com.zoeeasy.cloud.axino.service;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.axino.bean.*;


/**
 * 诺诺发票服务
 *
 * @author walkman
 * @since 2019-01-16
 */
public interface NuonuoService {

    /**
     * 开票请求
     *
     * @param params
     * @return
     */
    ObjectResultDto<NnPlaceOrderResultBean> placeInvoiceOrder(NnPlaceOrderRequestBean params);

    /**
     * 开票结果查询
     *
     * @param params
     * @return
     */
    ObjectResultDto<NnQueryOrderResultBean> queryInvoiceOrder(NnQueryInvoiceOrderRequestBean params);

    /**
     * 根据订单号查询发票请求流水号
     *
     * @param params
     * @return
     */
    ObjectResultDto<NnQueryOrderResultBean> queryInvoiceKaipiao(NnQueryInvoiceRequestBean params);

    /**
     * 发票作废申请
     *
     * @param params
     * @return
     */
    ObjectResultDto<NnPlaceOrderResultBean> invalidInvoice(NnPlaceOrderInvalidRequestBean params);
}
