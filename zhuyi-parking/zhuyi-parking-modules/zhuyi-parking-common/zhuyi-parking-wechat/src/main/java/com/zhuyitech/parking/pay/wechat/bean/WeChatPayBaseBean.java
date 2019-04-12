package com.zhuyitech.parking.pay.wechat.bean;

import com.zhuyitech.parking.pay.wechat.util.XStreamInitializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.UUID;

/**
 * <pre>
 *     微信支付请求对象共用的参数存放类
 * </pre>
 *
 * @author walkman
 * @date 2017-07-10-16:49
 */
public abstract class WeChatPayBaseBean {

    /**
     * 公众账号ID
     */
    @XStreamAlias("appid")
    protected String appid;

    /**
     * 商户号
     */
    @XStreamAlias("mch_id")
    protected String mchId;

    /**
     * 随机字符串
     */
    @XStreamAlias("nonce_str")
    protected String nonceStr;

    /**
     * 签名方式
     */
    @XStreamAlias("sign_type")
    protected String signType;

    /**
     * 签名
     */
    @XStreamAlias("sign")
    protected String sign;

    /**
     * 设置公用参数
     */
    public WeChatPayBaseBean() {

        this.nonceStr = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 32);
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 将当前bean转换为String类型的XML
     *
     * @return
     */
    public String toXML() {
        XStream xstream = XStreamInitializer.getInstance();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }

}
