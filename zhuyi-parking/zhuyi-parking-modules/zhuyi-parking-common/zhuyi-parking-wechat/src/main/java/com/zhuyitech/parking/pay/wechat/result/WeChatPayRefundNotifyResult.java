package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     微信退款通知
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-10:41
 */
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

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(Integer settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

    public Integer getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(Integer settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundRequestSource() {
        return refundRequestSource;
    }

    public void setRefundRequestSource(String refundRequestSource) {
        this.refundRequestSource = refundRequestSource;
    }
}
