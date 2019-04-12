package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 订单类型枚举
 *
 * @author zwq
 * @date 2018-06-26
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BizTypeAdEnum {

    /**
     * 全部
     */
    ALL(1, "全部"),

    /**
     * 充值
     */
    RECHARGE(2, "充值"),

    /**
     * 支付账单
     */
    PAYMENT(3, "支付账单"),
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
    public static BizTypeAdEnum parse(Integer value) {
        if (value != null) {
            BizTypeAdEnum[] array = values();
            for (BizTypeAdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
