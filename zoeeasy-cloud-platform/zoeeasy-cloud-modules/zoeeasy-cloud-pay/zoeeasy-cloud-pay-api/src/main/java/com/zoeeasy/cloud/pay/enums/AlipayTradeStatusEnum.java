package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付宝支付状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum AlipayTradeStatusEnum {

    /**
     * 交易创建，等待买家付款
     */
    WAIT_BUYER_PAY(1, "交易创建，等待买家付款"),

    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED(2, "未付款交易超时关闭，或支付完成后全额退款"),

    /**
     * 交易支付成功
     */
    TRADE_SUCCESS(3, "交易支付成功 "),

    /**
     * 交易结束，不可退款
     */
    TRADE_FINISHED(4, "交易结束，不可退款"),
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
    public static AlipayTradeStatusEnum parse(Integer value) {
        if (value != null) {
            AlipayTradeStatusEnum[] array = values();
            for (AlipayTradeStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
