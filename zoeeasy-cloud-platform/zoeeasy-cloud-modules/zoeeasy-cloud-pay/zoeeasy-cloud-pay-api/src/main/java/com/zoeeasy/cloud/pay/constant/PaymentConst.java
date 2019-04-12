package com.zoeeasy.cloud.pay.constant;


/**
 * 支付常量
 *
 * @author walkman
 */
public final class PaymentConst {

    /**
     * 最大支付金额(分)
     */
    public static final Integer MIN_PAYMENT_AMOUNT_FEN = 1;

    /**
     * 最大支付金额(分)
     */
    public static final Integer MAX_PAYMENT_AMOUNT_FEN = 1000000;

    public static final String DEFAULT_CURRENCY_TYPE = "RMB";

    public static final String PAYMENT_PAY_USER_NOT_NULL = "用户ID不能为空";

    public static final String PAYMENT_PAY_BIZ_TYPE_NOT_NULL = "业务类型不能为空";

    public static final String PAYMENT_PAY_BIZ_TYPE_INVALID = "业务类型无效";

    public static final String PAYMENT_PAY_BIZ_ORDER_NOT_NULL = "业务订单号不能为空";

    public static final String PAYMENT_PAY_WAY_NOT_NULL = "支付方式不能为空";

    public static final String PAYMENT_PAY_WAY_INVALID = "支付方式无效";

    public static final String PAYMENT_PAY_TYPE_NOT_NULL = "支付类型不能为空";

    public static final String PAYMENT_PAY_TYPE_INVALID = "支付类型无效";

    public static final String PAYMENT_PAY_AMOUNT_NOT_NULL = "支付金额不能为空";

    public static final String PAYMENT_PAY_AMOUNT_INVALID = "支付金额无效";

    public static final String PAYMENT_PAY_AMOUNT_LARGER = "支付金额超过上限";

    public static final String PAYMENT_PAY_ORDER_NO_NOT_NULL = "支付订单号不能为空";

    public static final String PAYMENT_PAY_ORDER_NOT_FOUND = "支付订单不存在";

    public static final String PAYMENT_ALIPAY_ORDER_NOT_FOUND = "支付宝订单不存在";

    public static final String PAYMENT_ALIPAY_ORDER_UPDATE_FAILED = "支付宝订单更新失败";

    public static final String PAYMENT_WXPAY_PARSE_FAILED = "微信支付通知解析失败";

    public static final String PAYMENT_WXPAY_ORDER_NOT_FOUND = "微信支付订单不存在";

    public static final String PAYMENT_WXPAY_ORDER_UPDATE_FAILED = "微信支付订单更新失败";

    public static final String PAYMENT_MESSAGE_SEND_FAILED = "支付成功消息发送失败";


}