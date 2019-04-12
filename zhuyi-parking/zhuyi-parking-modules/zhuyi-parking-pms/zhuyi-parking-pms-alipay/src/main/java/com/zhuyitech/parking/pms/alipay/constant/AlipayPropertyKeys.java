package com.zhuyitech.parking.pms.alipay.constant;


/**
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public final class AlipayPropertyKeys {

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static final String PAY_ALIPAY_APPID = "pay.alipay.appid";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static final String PAY_ALIPAY_APP_PRIVATEKEY = "pay.alipay.app_private_key";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    public static final String PAY_ALIPAY_PUBLICKEY = "pay.alipay.alipay_public_key";
}
