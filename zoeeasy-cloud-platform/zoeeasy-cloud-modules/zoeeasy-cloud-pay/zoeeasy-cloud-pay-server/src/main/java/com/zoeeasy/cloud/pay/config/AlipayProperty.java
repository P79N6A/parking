package com.zoeeasy.cloud.pay.config;

import com.zoeeasy.cloud.pay.constant.alipay.AlipayPropertyKeys;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 支付宝配置参数
 *
 * @author walkman
 * @since 2018/11/7
 */
@Configuration
@Getter
@Setter
public class AlipayProperty {

    @Value("${" + AlipayPropertyKeys.APP_ID_KEY + ":}")
    private String appId;

    @Value("${" + AlipayPropertyKeys.SEELER_ID_KEY + ":}")
    private String sellerId;

    @Value("${" + AlipayPropertyKeys.APP_PRIVATE_KEY + ":}")
    private String privateKey;

    @Value("${" + AlipayPropertyKeys.ALIPAY_PUBLIC_KEY + ":}")
    private String publicKey;

    @Value("${" + AlipayPropertyKeys.ALIPAY_FEE_RATE_KEY + ":0.06}")
    private Double feeRate;

    @Value("${" + AlipayPropertyKeys.RETURN_URL_KEY + ":}")
    private String returnUrl;

    @Value("${" + AlipayPropertyKeys.NOTIFY_URL_KEY + ":}")
    private String notifyUrl;

    @Value("${" + AlipayPropertyKeys.DOWN_URL_KEY + ":}")
    private String downloadUrl;

    /**
     * 支付宝小程序APPID
     */
    @Value("${" + AlipayPropertyKeys.MINI_APP_ID_KEY + ":}")
    private String miniAppId;

    /**
     * 支付宝小程序PrivateKey
     */
    @Value("${" + AlipayPropertyKeys.MINI_APP_PRIVATE_KEY + ":}")
    private String miniPrivateKey;

}
