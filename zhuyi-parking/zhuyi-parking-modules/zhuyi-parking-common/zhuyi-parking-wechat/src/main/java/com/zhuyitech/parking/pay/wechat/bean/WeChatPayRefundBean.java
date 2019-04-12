package com.zhuyitech.parking.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     申请退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:53
 */
@XStreamAlias("xml")
public class WeChatPayRefundBean extends WeChatPayBaseBean {

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
     * 订单金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 退款金额
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

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

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(Integer refundFee) {
        this.refundFee = refundFee;
    }

}
