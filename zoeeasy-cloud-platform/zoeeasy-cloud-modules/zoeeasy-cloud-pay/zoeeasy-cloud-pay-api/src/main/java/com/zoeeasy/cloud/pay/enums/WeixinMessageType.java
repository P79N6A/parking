package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 微信异步通知类型枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum WeixinMessageType {

    /**
     * 支付
     */
    PAYMENT(1, "支付"),
    /**
     * 退款
     */
    REFUND(2, "退款"),
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
    public static WeixinMessageType parse(Integer value) {
        if (value != null) {
            WeixinMessageType[] array = values();
            for (WeixinMessageType each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}