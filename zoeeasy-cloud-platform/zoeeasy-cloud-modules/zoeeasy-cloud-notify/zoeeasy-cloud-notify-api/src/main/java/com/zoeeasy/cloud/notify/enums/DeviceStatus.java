package com.zoeeasy.cloud.notify.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 设备状态枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum DeviceStatus {

    /**
     * 有效
     */
    VALID(1, "有效"),
    /**
     * 失效
     */
    INVALID(2, "失效");

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
    public static DeviceStatus parse(Integer value) {
        if (value != null) {
            DeviceStatus[] array = values();
            for (DeviceStatus each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
