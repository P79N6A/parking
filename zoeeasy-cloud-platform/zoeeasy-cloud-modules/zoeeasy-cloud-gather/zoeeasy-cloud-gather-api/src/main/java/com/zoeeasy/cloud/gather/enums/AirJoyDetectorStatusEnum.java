package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum AirJoyDetectorStatusEnum {

    OUT("0", "无车"),

    ON("1", "有车"),;

    /**
     * 值
     */
    @Getter
    private String value;

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
    public static AirJoyDetectorStatusEnum parse(Integer value) {
        if (value != null) {
            AirJoyDetectorStatusEnum[] array = values();
            for (AirJoyDetectorStatusEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }
}
