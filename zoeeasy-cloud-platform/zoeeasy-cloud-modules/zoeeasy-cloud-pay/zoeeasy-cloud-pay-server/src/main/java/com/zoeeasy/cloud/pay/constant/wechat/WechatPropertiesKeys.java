package com.zoeeasy.cloud.pay.constant.wechat;

/**
 * 微信支付配置常量
 *
 * @author walkman
 */
public final class WechatPropertiesKeys {

    private WechatPropertiesKeys() {
    }

    /**
     * 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     */
    public static final String NOTIFY_URL = "pay.wechat.notify_url";

    /**
     * 公众账号ID
     */
    public static final String WECHAT_PAY_APPID = "pay.wechat.appid";

    /**
     * 微信签名的key(key为商户平台设置的密钥key)
     */
    public static final String WECHAT_PAY_SIGN_KEY = "pay.wechat.sign_key";

    /**
     * 商户号
     */
    public static final String WECHAT_PAY_MCH_ID = "pay.wechat.mch_id";

    /**
     * 微信证书地址
     */
    public static final String WECHAT_CERTIFICATE_PATH = "pay.wechat.certificate.path";

    /**
     * 微信接口调用地址前缀
     */
    public static final String WECHAT_PAY_URL_PREFIX = "pay.wechat.pay.url.prefix";

    /**
     * 微信应用程序APPSECRET
     */
    public static final String WECHAT_PAY_APPSECRET = "pay.wechat.appSecret";

    /**
     * 微信支付费率
     */
    public static final String WECHAT_PAY_FEE_RATE = "pay.wechat.feeRate";

    /**
     * 微信公众号支付费率
     */
    public static final String WX_JSAPI_PAY_FEE_RATE = "pay.wechat.jsapi.feeRate";

    /**
     * 公众账号appId
     */
    public static final String WECHAT_JSAPI_APPID = "pay.wechat.jsapi.appid";

    /**
     * 微信应用程序APPSECRET
     */
    public static final String WECHAT_JSAPI_APPSECRET = "pay.wechat.jsapi.appSecret";

    /**
     * 公众号商户号
     */
    public static final String WECHAT_JSAPI_MCH_ID = "pay.wechat.jsapi.mch_id";

    /**
     * 微信公众号签名的key(key为商户平台设置的密钥key)
     */
    public static final String WECHAT_JSAPI_SIGN_KEY = "pay.wechat.jsapi.sign_key";

    /**
     * 微信小程序appId
     */
    public static final String WECHAT_MINI_APPID = "pay.wechat.mini.appid";

    /**
     * 微信小程序APPSECRET
     */
    public static final String WECHAT_MINI_APPSECRET = "pay.wechat.mini.appSecret";

    /**
     * 微信小程序商户号
     */
    public static final String WECHAT_MINI_MCH_ID = "pay.wechat.mini.mch_id";

    /**
     * 微信小程序签名的key(key为商户平台设置的密钥key)
     */
    public static final String WECHAT_MINI_SIGN_KEY = "pay.wechat.mini.sign_key";

}
