package com.zoeeasy.cloud.pay.constant.wechat;

/**
 * 微信公众号配置
 *
 * @author walkman
 * @since 2017-07-11-13:15
 */
public final class WechatConst {

    private WechatConst() {
    }

    /**
     * 微信支付类型(APP)
     */
    public static final String TRADE_TYPE_APP = "APP";

    /**
     * 微信支付类型(公众账号)
     */
    public static final String TRADE_TYPE_JSAPI = "JSAPI";

    /**
     * 微信支付类型(小程序支付)
     */
    public static final String TRADE_TYPE_MINI = "JSAPI";

    /**
     * 微信支付类型(Native支付)
     */
    public static final String TRADE_TYPE_NATIVE = "NATIVE";

    /**
     * 微信支付类型(H5支付支付)
     */
    public static final String TRADE_TYPE_MWEB = "MWEB";

    /**
     * 微信支付类型(付款码支付)
     */
    public static final String TRADE_TYPE_MICROPAY = "MICROPAY";

    /**
     * 签名的方式（默认为MD5）
     */
    public static final String SIGN_TYPE = "MD5";

    /**
     * 返回的code
     */
    public static final String WECHAT_SUCCESS = "SUCCESS";

    public static final String WECHAT_FAIL = "FAIL";

    public static final String WECHAT_PAY_URL = "https://api.mch.weixin.qq.com";

    /**
     * 统一下单
     */
    public static final String WECHAT_PAY_URL_UNIFIED_ORDER = "/pay/unifiedorder";

    /**
     * 查询订单
     */
    public static final String WECHAT_PAY_URL_ORDER_QUERY = "/pay/orderquery";

    /**
     * 关闭订单
     */
    public static final String WECHAT_PAY_URL_CLOSE_ORDER = "/pay/closeorder";

    /**
     * 申请退款
     */
    public static final String WECHAT_PAY_URL_REFUND = "/secapi/pay/refund";

    /**
     * 查询退款
     */
    public static final String WECHAT_PAY_URL_REFUND_QUERY = "/pay/refundquery";

    /**
     * 下载对账单
     */
    public static final String WECHAT_PAY_URL_DOWNLOADBILL = "/pay/downloadbill";

    /**
     * 转换短链接
     */
    public static final String WECHAT_PAY_URL_SHORT_URL = "/tools/shorturl";

}
