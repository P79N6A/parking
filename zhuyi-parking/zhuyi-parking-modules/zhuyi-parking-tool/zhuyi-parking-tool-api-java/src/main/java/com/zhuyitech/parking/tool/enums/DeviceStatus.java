package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 设备状态枚举
 *
 * @author walkman
 */
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
    private int value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    DeviceStatus(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

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
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

}
