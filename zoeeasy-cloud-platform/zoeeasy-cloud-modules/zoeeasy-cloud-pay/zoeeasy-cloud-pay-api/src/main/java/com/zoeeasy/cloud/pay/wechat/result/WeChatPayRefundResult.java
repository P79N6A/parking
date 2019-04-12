package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     申请退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:28
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayRefundResult extends WeChatPayBaseResult {

    /**
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 退款通道
     */
    @XStreamAlias("refund_channel")
    private String refundChannel;

    /**
     * 应结订单金额
     * (应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。)
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 退款金额
     */
    @XStreamAlias("refund_fee")
    private String refundFee;

    /**
     * 标价金额
     */
    @XStreamAlias("total_fee")
    private String totalFee;

    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private String cashFee;
}
