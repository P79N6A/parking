package com.zoeeasy.cloud.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author AkeemSuper
 * @date 2018/12/1 0001
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ChargeFreeTypeEnum {

    FIX_CAR_FREE(1, "固定车有效期内免费"),

    VISIT_CAR_FREE(2, "访客车辆有效期内免费"),

    PACKET_CAR_FREE(3, "包期车有效期内免费"),

    WHITE_CAR_FREE(4, "白名单有效期内免费"),

    CALCULATE_FREE(5, "计算免费"),
    CHARGE_RULE_EMPTY_FREE(6, "收费规则不存在免费");

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
    public static ChargeFreeTypeEnum parse(Integer value) {
        if (value != null) {
            ChargeFreeTypeEnum[] array = values();
            for (ChargeFreeTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
