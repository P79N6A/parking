package com.zhuyitech.parking.pay.alipay.config;

import com.zhuyitech.parking.pay.alipay.constant.AlipayPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置参数
 */
@Configuration
public class AlipayConfig {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    @Value("${" + AlipayPropertyKeys.APP_ID + ":}")
    @Getter
    @Setter
    private String appId;

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    @Value("${" + AlipayPropertyKeys.APP_PRIVATE_KEY + ":}")
    @Getter
    @Setter
    private String appPrivateKey;

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    @Value("${" + AlipayPropertyKeys.ALIPAY_PUBLIC_KEY + ":}")
    @Getter
    @Setter
    private String alipayPublicKey;

    /**
     */
    @Value("${" + AlipayPropertyKeys.SELLER_ID + ":}")
    @Getter
    @Setter
    private String sellerId;
}
