package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 设备厂商枚举
 */
@NoArgsConstructor
@AllArgsConstructor
public enum DeviceProviderEnum {

    AIRJOY(100, "艾佳"),

    INMOTION(200, "易姆讯"),

    HIKVISION(300, "海康"),

    FUSHANG(400, "富尚"),
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
    public static DeviceProviderEnum parse(Integer value) {
        if (value != null) {
            DeviceProviderEnum[] array = values();
            for (DeviceProviderEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
