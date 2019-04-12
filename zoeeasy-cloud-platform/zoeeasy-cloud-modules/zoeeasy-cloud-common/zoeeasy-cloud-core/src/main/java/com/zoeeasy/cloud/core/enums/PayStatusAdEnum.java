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
public enum PayStatusAdEnum {

    /**
     * 全部
     */
    ALL(1, "全部支付状态"),

    /**
     * 未支付
     */
    PAY_WAITING(2, "未支付"),

    /**
     * 已退款
     */
    PAY_SUCCESS(3, "已支付"),

    /**
     * 已退款
     */
    REFUND(4, "已退款"),
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
    public static PayStatusAdEnum parse(Integer value) {
        if (value != null) {
            PayStatusAdEnum[] array = values();
            for (PayStatusAdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
