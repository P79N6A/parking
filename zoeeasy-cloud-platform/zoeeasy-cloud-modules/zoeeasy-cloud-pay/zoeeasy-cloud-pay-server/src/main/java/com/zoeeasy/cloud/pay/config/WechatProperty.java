package com.zoeeasy.cloud.pay.config;

import com.zoeeasy.cloud.pay.constant.wechat.WechatPropertiesKeys;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author AkeemSuper
 * @date 2018/11/7 0007
 */
@Component
@Data
@EqualsAndHashCode(callSuper = false)
public class WechatProperty {

    /**
     * 微信接口调用地址前缀
     */
    @Value("${pay.wechat.pay.url.prefix:}")
    private String prefix;

    /**
     * 服务器异步通知页面路径
     */
    @Value("${pay.wechat.notify_url:http://183.129.243.130:10186/cloud/pay/notify/weixin}")
    private String notifyUrl;

    /**
     * APP支付APPID
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_PAY_APPID + ":}")
    private String appId;

    /**
     * 微信签名的key(key为商户平台设置的密钥key)
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_PAY_SIGN_KEY + ":}")
    private String signKey;

    /**
     * 商户号
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_PAY_MCH_ID + ":}")
    private String mchId;

    /**
     * 微信证书地址
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_CERTIFICATE_PATH + ":}")
    private String certificatePath;

    /**
     * 微信应用程序APPSECRET
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_PAY_APPSECRET + ":}")
    private String appSecret = "pay.wechat.appSecret";

    /**
     * 微信支付费率
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_PAY_FEE_RATE + ":0.10}")
    private Double feeRate;

    /**
     * 公众账号appId
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_JSAPI_APPID + ":}")
    private String jsApiAppId;

    /**
     * 微信应用程序APPSECRET
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_JSAPI_APPSECRET + ":}")
    private String jsApiAppSecret;

    /**
     * 公众号商户号
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_JSAPI_MCH_ID + ":}")
    private String jsMchId;

    /**
     * 微信公众号签名的key(key为商户平台设置的密钥key)
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_JSAPI_SIGN_KEY + ":}")
    private String jsApiSinKey;

    /**
     * 微信公众号支付费率
     */
    @Value("${" + WechatPropertiesKeys.WX_JSAPI_PAY_FEE_RATE + ":0.10}")
    private Double jsFeeRate;

    /**
     * 微信小程序商户号
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_MINI_MCH_ID + ":}")
    private String wxMiniMchId;

    /**
     * 微信小程序appId
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_MINI_APPID + ":}")
    private String wxMiniAppId;

    /**
     * 微信小程序APPSECRET
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_MINI_APPSECRET + ":}")
    private String wxMiniAppSecret;

    /**
     * 微信小程序签名的key(key为商户平台设置的密钥key)
     */
    @Value("${" + WechatPropertiesKeys.WECHAT_MINI_SIGN_KEY + ":}")
    private String wxMiniApiSignKey;

}
