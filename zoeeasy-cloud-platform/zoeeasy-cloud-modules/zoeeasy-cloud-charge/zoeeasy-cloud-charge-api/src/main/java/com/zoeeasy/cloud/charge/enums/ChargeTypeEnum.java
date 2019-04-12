package com.zoeeasy.cloud.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 预约状态后台枚举
 *
 * @author zwq
 * @date 201-06-27
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ChargeTypeEnum {

    GIST_TO_DATE(1, "按时收费"),

    GIST_TO_TIMES(2, "按次收费"),
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
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
