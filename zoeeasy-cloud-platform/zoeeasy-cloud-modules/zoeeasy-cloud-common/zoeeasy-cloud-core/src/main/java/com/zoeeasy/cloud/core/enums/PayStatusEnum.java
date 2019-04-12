package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayStatusEnum {

    /**
     * 订单已创建
     */
    CREATED(0, "订单已创建"),

    /**
     * 等待支付
     */
    PAY_WAITING(1, "等待支付"),

    /**
     * 支付中,等待支付处理结果
     */
    WAITING_PAYMENT_RESULT(2, "支付中"),

    /**
     * 支付成功
     */
    PAY_SUCCESS(3, "支付成功"),

    /**
     * 支付超时
     */
    PAY_TIMEOUT(4, "支付超时"),

    /**
     * 支付取消
     */
    PAY_CANCELED(5, "支付取消"),

    /**
     * 支付错误
     */
    PAY_ERROR(6, "支付错误"),

    /**
     * 支付失败
     */
    PAY_FAILED(7, "支付失败"),
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
    public static PayStatusEnum parse(Integer value) {
        if (value != null) {
            PayStatusEnum[] array = values();
            for (PayStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
