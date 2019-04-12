package com.zhuyitech.parking.pay.wechat.config;


import com.zhuyitech.parking.pay.wechat.constant.WechatPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号配置
 *
 * @author walkman
 * @date 2017-07-11-13:15
 */
@Configuration
public class WechatConfig {

    /**
     * 公众账号ID
     */
    @Value("${" + WechatPropertyKeys.WECHAT_PAY_APPID + ":}")
    @Getter
    @Setter
    private String wechatPayAppid;

    /**
     * 商户号
     */
    @Value("${" + WechatPropertyKeys.WECHAT_PAY_MCH_ID + ":}")
    @Getter
    @Setter
    private String wechatPayMchId;

    /**
     * 微信证书地址
     */
    @Value("${" + WechatPropertyKeys.WECHAT_CERTIFICATE_PATH + ":}")
    @Getter
    @Setter
    private String wechatCertificatePath;

    /**
     * 微信应用程序APPSECRET
     */
    @Value("${" + WechatPropertyKeys.WECHAT_PAY_APPSECRET + ":}")
    @Getter
    @Setter
    private String wechatPayAppsecret;

    /**
     * 微信证书地址
     */
    @Value("${" + WechatPropertyKeys.WECHAT_JSAPI_APPID + ":}")
    @Getter
    @Setter
    private String wechatJsapiAppid;

    /**
     * 微信应用程序APPSECRET
     */
    @Value("${" + WechatPropertyKeys.WECHAT_JSAPI_APPSECRET + ":}")
    @Getter
    @Setter
    private String wechatJsapiAppsecret;
}
