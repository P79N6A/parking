package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付方式枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayWayEnum {

    /**
     * 支付宝支付
     */
    ALIPAY(1, "支付宝"),

    /**
     * 微信支付
     */
    WEIXINPAY(2, "微信"),

    /**
     * 钱包支付
     */
    PACKET(3, "钱包支付"),

    /**
     * 银联支付
     */
    UNIONPAY(4, "银联"),

    /**
     * 支付宝收款
     */
    ALIPAY_CASH(5, "支付宝收款"),

    /**
     * 微信收款
     */
    WECHAT_CASH(6, "微信收款"),

    /**
     * 现金收款
     */
    CASH(7, "现金收款"),
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
    public static PayWayEnum valueOf(Integer value) {
        if (value != null) {
            PayWayEnum[] array = values();
            for (PayWayEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据注释获取对应的枚举
     *
     * @param comment 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PayWayEnum parseByComment(String comment) {
        if (comment != null) {
            PayWayEnum[] array = values();
            for (PayWayEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}

