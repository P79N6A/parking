package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     查询退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatRefundQueryParams extends WeChatPayBaseParam {

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    private String refundId;
}
