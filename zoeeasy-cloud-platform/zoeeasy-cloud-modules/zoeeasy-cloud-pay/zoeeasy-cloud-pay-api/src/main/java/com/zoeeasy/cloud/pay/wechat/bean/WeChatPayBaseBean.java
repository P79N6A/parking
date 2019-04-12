package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.zoeeasy.cloud.pay.wechat.util.XStreamInitializer;
import lombok.Data;

import java.util.UUID;

/**
 * <pre>
 *     微信支付请求对象共用的参数存放类
 * </pre>
 *
 * @author walkman
 * @date 2017-07-10-16:49
 */
@Data
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
