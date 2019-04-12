package com.zoeeasy.cloud.axino.constant;

/**
 * 静态用户数据
 *
 * @author sdk.jss.com.cn
 * @version 2.0
 * @since jdk1.6
 */
public class StateData {

    /**
     * APP 调用接口需填写以下系统参数
     */
    public static String app_secret = ""; // 填写本APP申请的 appSecret,注意区分正式环境与沙箱环境参数，如： 9B4CA8CEA5EE4823
    public static String app_key = ""; // 填写本APP申请的 appKey,注意区分正式环境与沙箱环境参数，如：fk5dWkPA
    public static String app_api = ""; // 填写APP调用接口名称，如：nuonuo.speedBilling.billingByCode
    public static String app_apiVersion = "V1.0.0"; // 填写本APP调用接口版本，如：1.0.0
    public static String app_accessToken = ""; // 填写本APP申请的 令牌 ,注意区分正式环境与沙箱环境参数，如：89ad5e3430027d548d5c21a785d31d73
    public static String app_dataType = "JSON"; // 填写本APP传输数据格式，如：JSON,XML
    public static String app_compressType = ""; // 填写本APP传输数据压缩格式，如：GZIP
    public static String app_signType = "AES/AES"; // 填写本APP传输数据加密格式，如：AES/AES
    public static String user_Tax = ""; // 业务发生方税号（ISV类型用户必填）；如：339901999999142

    /**
     * APP 请求并发数（平台默认）
     */
    /**
     * 填写APP并发请求数
     */
    public static String app_rate = "10"; //

    /**
     * 发送方式
     */
    public static String contentType = "application/x-www-form-urlencoded";

    /**
     * 开放平台API访问地址
     */
    /**
     * 正式环境地址
     */
    public static String url = "https://sdk.jss.com.cn/openPlatform/services";

    /**
     * 沙箱环境地址
     */
    public static String url_sbox = "https://sandbox.jss.com.cn/openPlatform/services";

    /**
     * 开放平台Oauth授权访问地址
     */
    /**
     * 正式环境地址
     */
    public static String url_token = "https://open.jss.com.cn/accessToken"; //

    /**
     * 正式环境/沙箱环境OAUTH参数
     * 请求access_token环节
     */
    public static String auth_code = ""; // ISV商户获取的auth_code
    public static String refresh_token = ""; // ISV商户刷新令牌，普通商户无此值，可程序赋值
    public static String taxNum = ""; // 获取auth_code时开放平台返回当前商户税号，ISV模式下商户请求API时需传此值，可程序赋值
    public static String userId = ""; // 获取access_token时开放平台返回，ISV模式下商户刷新令牌时需传此值，可程序赋值
    public static String expires_in = ""; // access_token失效时间，可程序赋值
    public static String client_id = app_key; // 用户ID，取$appKey值，当调用刷新令牌时取$userId值
    public static String client_secret = app_secret; // 用户秘钥，取$app_secret值
    public static String redirect_uri = "http://open.jss.com.cn"; // 对调地址由商户自定义
    public static String grant_type_token = "authorization_code"; // 获取access_token时，OAUTH定义编码类型
    public static String grant_type_refreshToken = "refresh_token"; // 获取access_token时，OAUTH定义编码类型
    public static String accessToken_url = url_token; // 获取access_token和refresh_token请求地址

    /**
     * 定时任务 刷新access_token
     */
    public static long delay = 60; // 步长
    public static long preiod = 29 * 24 * 60 * 60; // 偏移量accesstoken尝试时间

}
