package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付模块结果枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum PayResultEnum {

    //支付
    PAY_RECHARGE_WAY_ERROR(30100, "充值方式无效"),
    PAY_RECHARGE_WAY_NOT_SUPPORT(30101, "充值方式不支持"),
    PAY_RECHARGE_AMOUNT_ERROR(30102, "充值金额无效"),
    PAY_RECHARGE_AMOUNT_TOO_LARGE(30103, "充值金额过大"),
    PAY_RECHARGE_FAILED(30104, "充值失败"),
    PAY_WAY_NOT_SUPPORT(30105, "支付方式不支持"),
    TRADE_PASSWORD_EMPTY(30106, "支付密码为空"),
    TRADE_PASSWORD_ERR(30107, "支付密码有误"),
    TRADE_PASSWORD_NOT_VERIFY(30108, "支付密码未校验"),
    //微信支付
    PARKING_RECORD_EMPTY(30200, "订单不存在"),
    PAY_WAY_EMPTY(30201, "支付方式为空"),
    AMOUNT_ERR(30202, "订单金额有误"),
    //支付宝支付
    ALIPAY_PLACE_ORDER_ERROR(30203, "支付宝下单失败"),
    WECHAT_PLACE_ORDER_ERROR(30204, "微信下单失败"),
    ORDER_AMOUNT_ERROR(30205, "订单金额不一致"),
    ORDER_PAY_STATUS_ERROR(30205, "订单支付状态无效"),
    RECHARGE_ORDER_NOT_FOUND(30206, "充值订单不存在"),
    RECHARGE_ORDER_AMOUNT_ERROR(30207, "充值金额不一致"),
    PARKING_ORDER_PAY_FAILED(30208, "订单支付失败"),
    APPOINT_ORDER_EMPTY(30209, "预定订单号不能为空"),
    APPOINT_ORDER_NOT_FOUND(30210, "预定订单不存在"),
    APPOINT_ORDER_PAY_FAILED(30211, "预定订单支付失败"),
    BALANCE_CHECK_FAILED(30212, "余额检测失败"),
    PAYMENT_AMOUNT_NOT_ENOUGH(30213, "余额不足"),
    OPENID_EMPTY(30214, "openid为空"),
    UNIONID_EMPTY(30215, "unionid为空"),
    ORDER_PAYED(30216, "账单已支付"),
    PARKING_ORDER_PAYED_CANT_REPEAT(30217, "停车订单已支付,请勿重复支付"),
    TRADE_PLACE_ORDER_ERROR(30218, "停车订单已支付"),
    ;

    /**
     * 值
     */
    @Getter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PayResultEnum parse(Integer value) {
        if (value != null) {
            PayResultEnum[] array = values();
            for (PayResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
