package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 海康平台停车账单状态
 *
 * @author walkman
 * @date 2018/1/26
 */
@NoArgsConstructor
@AllArgsConstructor
public enum HikParkingPaymentStatusEnum {

    /**
     *
     */
    CREATED(0, "已创建"),

    PAYING(1, "支付中"),

    PAY_WAITING(2, "等待支付结果"),

    PAY_SUCCESS(3, "支付成功"),

    PAY_FAIL(4, "支付失败"),
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
    public static HikParkingPaymentStatusEnum parse(Integer value) {
        if (value != null) {
            HikParkingPaymentStatusEnum[] array = values();
            for (HikParkingPaymentStatusEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}
