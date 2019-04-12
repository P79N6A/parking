package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 操作状态
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessStatus {

    /**
     * 失败
     */
    FAIL(0, "失败"),

    /**
     * 成功
     */
    SUCCESS(1, "成功");

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
    public static BusinessStatus parse(Integer value) {
        if (value != null) {
            BusinessStatus[] array = values();
            for (BusinessStatus each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
