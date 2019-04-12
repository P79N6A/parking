package com.zoeeasy.cloud.pay.constant.alipay;

/**
 * 支付宝配置常量
 *
 * @author walkman
 */
public final class AlipayPropertyKeys {

    private AlipayPropertyKeys() {
    }

    /**
     * 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static final String APP_ID_KEY = "pay.alipay.appid";

    /**
     *
     */
    public static final String SEELER_ID_KEY = "pay.alipay.sellerId";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    public static final String APP_PRIVATE_KEY = "pay.alipay.app_private_key";

    /**
     * 支付宝平台支付费率
     */
    public static final String ALIPAY_FEE_RATE_KEY = "pay.alipay.feeRate";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    public static final String ALIPAY_PUBLIC_KEY = "pay.alipay.alipay_public_key";

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static final String NOTIFY_URL_KEY = "pay.alipay.notify_url";

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static final String RETURN_URL_KEY = "pay.alipay.return_url";

    /**
     *
     */
    public static final String DOWN_URL_KEY = "pay.alipay.bill_download_directory";

    /**
     * 支付宝小程序应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     */
    public static final String MINI_APP_ID_KEY = "pay.alipay.mini.appid";

    /**
     * 支付宝小程序商户秘钥
     */
    public static final String MINI_APP_PRIVATE_KEY = "pay.alipay.mini.app_private_key";

}
