package com.zhuyitech.parking.pay.wechat.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *   统一下单 (在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"返回的结果)
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-11:14
 */
@XStreamAlias("xml")
public class WeChatPayUnifiedOrderResult extends WeChatPayBaseResult {

    /**
     * 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
     */
    @XStreamAlias("prepay_id")
    private String prepayId;

     /**
     * 交易类型，取值为：JSAPI，NATIVE，APP等
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * trade_type为NATIVE时有返回，用于生成二维码，展示给用户进行扫码支付
     */
    @XStreamAlias("code_url")
    private String codeURL;

    /**
     * 时间戳
     */
    @XStreamAlias("timeStamp")
    private String timeStamp;

    /**
     * 固定值
     */
    @XStreamAlias("pack")
    private String pack;

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getCodeURL() {
        return codeURL;
    }

    public void setCodeURL(String codeURL) {
        this.codeURL = codeURL;
    }
}
