package com.zhuyitech.parking.pay.alipay.constant;


/**
 * @author zwq
 * @date 2018/4/16 0016
 */
public interface AlipayPropertyKeys {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    String APP_ID = "pay.alipay.appid";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    String APP_PRIVATE_KEY = "pay.alipay.app_private_key";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    String ALIPAY_PUBLIC_KEY = "pay.alipay.alipay_public_key";

    /**
     *
     */
    String SELLER_ID = "pay.alipay.sellerId";
}
