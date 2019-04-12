package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;


/**
 * <pre>
 *     微信退款记录
 * </pre>
 *
 * @author walkman
 * @date 2017-07-13-15:03
 */
public class WeChatRefundRecord {

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
     * 退款渠道
     */
    @XStreamAlias("refund_channel")
    private String refundChannel;

    /**
     * 申请退款金额
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * 退款金额
     * 退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * 退款资金来源
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款/基本账户, REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * 退款状态
     */
    @XStreamAlias("refund_status")
    private String refundStatus;

    /**
     * 退款入账账户
     */
    @XStreamAlias("refund_recv_accout")
    private String refundRecvAccout;

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

    public String getRefundAccount() {
        return refundAccount;
    }

    public void setRefundAccount(String refundAccount) {
        this.refundAccount = refundAccount;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundRecvAccout() {
        return refundRecvAccout;
    }

    public void setRefundRecvAccout(String refundRecvAccout) {
        this.refundRecvAccout = refundRecvAccout;
    }
}
