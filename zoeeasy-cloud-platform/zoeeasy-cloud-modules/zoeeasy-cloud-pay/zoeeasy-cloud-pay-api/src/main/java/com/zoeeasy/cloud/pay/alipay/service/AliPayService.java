package com.zoeeasy.cloud.pay.alipay.service;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.alipay.params.*;
import com.zoeeasy.cloud.pay.alipay.result.*;

/**
 * <pre>
 *    支付宝支付接口
 * </pre>
 *
 * @author zwq
 * @date 2018-1-11-10:22
 */
public interface AliPayService {

    /**
     * 统一收单线下交易预创建
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayPrecreateOrderResult> precreateOrder(AlipayPrecreateOrderParams params);

    /**
     * 支付宝预支付
     *
     * @param
     * @return
     */
    ObjectResultDto<AliPayPrepareOrderResult> preparePay(AlipayPrepareOrderParams params);

    /**
     * 查询订单
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayPayTradeQueryResult> queryOrder(AlipayTradeQueryParams params);

    /**
     * 关闭订单
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayTradeCloseResult> closeOrder(AlipayTradeCloseParam params);

    /**
     * 交易退款
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayTradeRefundResult> refundOrder(AlipayTradeRefundParam params);

    /**
     * 交易退款查询
     *
     * @param params
     * @return
     */
    ObjectResultDto<AlipayTradeRefundQueryResult> queryRefundOrder(AlipayTradeRefundQueryParam params);

    /**
     * 获取对账单
     *
     * @param params
     * @return
     */
    ListResultDto<AlipayDownloadBillResult> downloadBill(AlipayBillDownloadParam params);


    ObjectResultDto<AliPayPrepareOrderResult> prepareAliPay(AlipayPrepareOrderParams params, String payOrderNo, String productName);


}
