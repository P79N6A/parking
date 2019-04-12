package com.zoeeasy.cloud.pay.wechat.service;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pay.wechat.params.*;
import com.zoeeasy.cloud.pay.wechat.result.*;

/**
 * <pre>
 *     微信公众号支付接口
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:22
 */
public interface WeChatPayService {

    /**
     * 统一下单
     *
     * @param unifiedOrderParams
     * @return
     */
    ObjectResultDto<WeChatPayUnifiedOrderResult> unifiedOrder(WeChatUnifiedOrderParams unifiedOrderParams);

    /**
     * 查询订单
     *
     * @param orderQueryParams
     * @return
     */
    ObjectResultDto<WeChatPayOrderQueryResult> orderQuery(WeChatOrderQueryParams orderQueryParams);

    /**
     * 关闭订单
     *
     * @param closeOrderParams
     * @return
     */
    ObjectResultDto<WeChatPayCloseOrderResult> closeOrder(WeChatCloseOrderParams closeOrderParams);

    /**
     * 申请退款
     *
     * @param refundParams
     * @return
     */
    ObjectResultDto<WeChatPayRefundResult> refund(WeChatRefundParams refundParams);

    /**
     * 查询退款
     *
     * @param refundQueryParams
     * @return
     */
    ObjectResultDto<WeChatPayRefundQueryResult> refundQuery(WeChatRefundQueryParams refundQueryParams);

    /**
     * 支付通知接口
     *
     * @param notifyXML
     * @return
     */
    ObjectResultDto<WeChatPayOrderNotifyResult> orderNotify(String notifyXML);

    /**
     * 退款通知接口
     *
     * @param notifyXML
     * @return
     */
    ObjectResultDto<WeChatPayRefundNotifyResult> refundNotify(String notifyXML);

    /**
     * 微信对账单结果
     *
     * @param downloadBillParams
     * @return
     */
    ObjectResultDto<WeChatPayDownloadBillResult> downloadBill(WeChatDownloadBillParams downloadBillParams);

    /**
     * 转换短链接
     *
     * @param shortenUrlParams
     * @return
     */
    ObjectResultDto<WeChatShortenUrlResult> shortenUrl(WeChatShortenUrlParams shortenUrlParams);

}
