package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *     微信支付结果通知
 * </pre>
 *
 * @author walkman
 * @date 2017-07-12-10:39
 */
@XStreamAlias("xml")
public class WeChatPayOrderNotifyResult extends WeChatPayBaseResult {

    private static final long serialVersionUID = 5403915269945081741L;
    /**
     * device_info
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 是否关注公众账号
     */
    @XStreamAlias("is_subscribe")
    private String isSubscribed;

    /**
     * 货币类型
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * 现金支付货币类型
     */
    @XStreamAlias("cash_fee_type")
    private String cashFeeType;

    /**
     * 代金券金额
     */
    @XStreamAlias("coupon_fee")
    private Integer couponFee;

    /**
     * 代金券数量
     */
    @XStreamAlias("coupon_count")
    private Integer couponCount;

    /**
     * 商家数据包
     */
    @XStreamAlias("attach")
    private String attach;

    /**
     * 用户标识
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * 交易类型
     * JSAPI、JSAPI、NATIVE、APP
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 付款银行
     * 银行类型，采用字符串类型的银行标识，银行类型见银行列表
     */
    @XStreamAlias("bank_type")
    private String bankType;

    /**
     * 订单总金额，单位为分
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 现金支付金额
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * 微信支付订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 支付完成时间
     */
    @XStreamAlias("time_end")
    private String timeEnd;

    /**
     * coupon_type
     */
    @XStreamAlias("coupon_type")
    private String couponType;

    /**
     * sign_type
     */
    @XStreamAlias("sign_type")
    private String signType;

    /**
     * settlement_total_fee
     */
    @XStreamAlias("settlement_total_fee")
    private String settlementTotalFee;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Integer getCashFee() {
        return cashFee;
    }

    public void setCashFee(Integer cashFee) {
        this.cashFee = cashFee;
    }

    public Integer getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(String isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getCashFeeType() {
        return cashFeeType;
    }

    public void setCashFeeType(String cashFeeType) {
        this.cashFeeType = cashFeeType;
    }

    public Integer getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(Integer couponFee) {
        this.couponFee = couponFee;
    }

    public Integer getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(Integer couponCount) {
        this.couponCount = couponCount;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSettlementTotalFee() {
        return settlementTotalFee;
    }

    public void setSettlementTotalFee(String settlementTotalFee) {
        this.settlementTotalFee = settlementTotalFee;
    }

}
