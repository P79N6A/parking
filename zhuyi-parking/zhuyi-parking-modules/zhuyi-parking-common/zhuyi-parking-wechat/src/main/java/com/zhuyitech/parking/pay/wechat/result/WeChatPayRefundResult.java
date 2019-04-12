package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     申请退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:28
 */
@XStreamAlias("xml")
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

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public String getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(String refundFee) {
        this.refundFee = refundFee;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getCashFee() {
        return cashFee;
    }

    public void setCashFee(String cashFee) {
        this.cashFee = cashFee;
    }
}
