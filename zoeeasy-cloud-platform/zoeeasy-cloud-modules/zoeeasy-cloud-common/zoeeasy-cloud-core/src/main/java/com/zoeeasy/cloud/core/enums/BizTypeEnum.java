package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 业务类型枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BizTypeEnum {

    /**
     * 充值
     */
    RECHARGE(1, "充值"),

    /**
     * 缴费
     */
    PAYMENT(2, "缴费"),

    /**
     * 预约
     */
    APPOINTMENT(3, "预约"),
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
    public static BizTypeEnum parse(Integer value) {
        if (value != null) {
            BizTypeEnum[] array = values();
            for (BizTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
