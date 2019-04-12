package com.zoeeasy.cloud.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     微信退款通知
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-10:41
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayRefundNotifyResult extends WeChatPayBaseResult {

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
     * 微信退款单号
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 订单金额
     * (订单总金额，单位为分，只能为整数，详见支付金额)
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 应结订单金额
     * (应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。)
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * 申请退款金额(退款总金额,单位为分)
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * 退款状态
     */
    @XStreamAlias("refund_status")
    private String refundStatus;

    /**
     * 退款成功时间
     */
    @XStreamAlias("success_time")
    private String successTime;

    /**
     * 退款入账账户
     */
    @XStreamAlias("refund_recv_accout")
    private String refundRecvAccout;

    /**
     * 退款资金来源
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * 退款发起来源
     */
    @XStreamAlias("refund_request_source")
    private String refundRequestSource;
}
