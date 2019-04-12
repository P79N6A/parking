package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * 收费方式枚举
 *
 * @author walkman
 * @date 2019/01/17
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ChargeTypeEnum {

    /**
     * 收费模式 1: 离场后缴费 2: 缴费后离场
     */
    AFTER(1, "离场后缴费"),

    BEFORE(2, "缴费后离场"),
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
    public static ChargeTypeEnum parse(Integer value) {
        if (value != null) {
            ChargeTypeEnum[] array = values();
            for (ChargeTypeEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}