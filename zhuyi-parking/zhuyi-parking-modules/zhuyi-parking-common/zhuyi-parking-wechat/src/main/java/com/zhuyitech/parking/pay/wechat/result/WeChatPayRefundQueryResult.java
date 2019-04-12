package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;

/**
 * <pre>
 *     查询退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:33
 */
public class WeChatPayRefundQueryResult extends WeChatPayBaseResult {

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
     * 现金支付金额
     * (现金支付金额，单位为分，只能为整数，详见支付金额)
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * 退款记录数
     */
    @XStreamAlias("refund_count")
    private Integer refundCount;

    /**
     * 退款记录集合
     */
    private List<WeChatRefundRecord> refundRecords;

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

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getRefundCount() {
        return refundCount;
    }

    public void setRefundCount(Integer refundCount) {
        this.refundCount = refundCount;
    }

    public List<WeChatRefundRecord> getRefundRecords() {
        return refundRecords;
    }

    public void setRefundRecords(List<WeChatRefundRecord> refundRecords) {
        this.refundRecords = refundRecords;
    }

    /**
     * 组装生成退款记录属性的内容
     */
    public void composeRefundRecords() {
        if (null == this.refundCount || refundCount <= 0) {
            return;
        }
        for (int i = 0; i < refundCount; i++) {
            WeChatRefundRecord refundRecord = new WeChatRefundRecord();

            refundRecord.setOutRefundNo(this.getXmlValue("xml/out_refund_no_" + i));
            refundRecord.setRefundId(this.getXmlValue("xml/refund_id_" + i));
            refundRecord.setRefundChannel(this.getXmlValue("xml/refund_channel_" + i));
            refundRecord.setRefundFee(this.getXmlValueAsInt("xml/refund_fee_" + i));
            refundRecord.setSettlementRefundFee(this.getXmlValueAsInt("xml/settlement_refund_fee_" + i));
            refundRecord.setRefundStatus(this.getXmlValue("xml/refund_status_" + i));
            refundRecord.setRefundRecvAccout(this.getXmlValue("xml/refund_recv_accout_" + i));

            this.refundRecords.add(refundRecord);
        }
    }

}
