package com.zhuyitech.parking.pay.wechat.result;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

/**
 * <pre>
 *     微信对账单（不包含对账总量）
 * </pre>
 *
 * @author walkman
 * @date 2017-07-24-14:14
 */
public class WeChatPayBaseBillResult extends BaseDto {

    /**
     * 交易时间
     */
    private String tradeTime;

    /**
     * 公众账号ID
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 子商户号
     */
    private String subMchId;

    /**
     * 设备号
     */
    private String deviceInfo;
    /**
     * 微信订单号
     */
    private String transationId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 用户标识
     */
    private String openId;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易状态
     */
    private String tradeState;

    /**
     * 付款银行
     */
    private String bankType;

    /**
     * 货币种类
     */
    private String feeType;

    /**
     * 总金额
     */
    private String totalFee;

    /**
     * 企业红包金额
     */
    private String couponFee;

    /**
     * 微信退款单号
     */
    private String refundId;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 退款金额
     */
    private String settlementRefundFee;

    /**
     * 企业红包退款金额
     */
    private String couponRefundFee;

    /**
     * 退款类型
     */
    private String refundChannel;

    /**
     * 退款状态
     */
    private String refundState;

    /**
     * 商品名称
     */
    private String body;

    /**
     * 商户数据包
     */
    private String attach;

    /**
     * 手续费
     */
    private String poundage;

    /**
     * 费率
     */
    private String poundageRate;

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getSubMchId() {
        return subMchId;
    }

    public void setSubMchId(String subMchId) {
        this.subMchId = subMchId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getTransationId() {
        return transationId;
    }

    public void setTransationId(String transationId) {
        this.transationId = transationId;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeState() {
        return tradeState;
    }

    public void setTradeState(String tradeState) {
        this.tradeState = tradeState;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getCouponFee() {
        return couponFee;
    }

    public void setCouponFee(String couponFee) {
        this.couponFee = couponFee;
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

    public String getSettlementRefundFee() {
        return settlementRefundFee;
    }

    public void setSettlementRefundFee(String settlementRefundFee) {
        this.settlementRefundFee = settlementRefundFee;
    }

    public String getCouponRefundFee() {
        return couponRefundFee;
    }

    public void setCouponRefundFee(String couponRefundFee) {
        this.couponRefundFee = couponRefundFee;
    }

    public String getRefundChannel() {
        return refundChannel;
    }

    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public String getPoundageRate() {
        return poundageRate;
    }

    public void setPoundageRate(String poundageRate) {
        this.poundageRate = poundageRate;
    }
}