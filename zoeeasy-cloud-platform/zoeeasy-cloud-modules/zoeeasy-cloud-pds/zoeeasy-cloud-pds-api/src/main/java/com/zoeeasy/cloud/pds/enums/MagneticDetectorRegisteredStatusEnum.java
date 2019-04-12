package com.zoeeasy.cloud.pds.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 返回结果枚举
 *
 * @author lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MagneticDetectorRegisteredStatusEnum {
    /**
     * 0 未注册
     */
    UNREGISTERED(0, "未注册"),

    /**
     * 1 已注册
     */
    REGISTERED(1, "已注册");

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
    public static MagneticDetectorRegisteredStatusEnum parse(Integer value) {
        if (value != null) {
            MagneticDetectorRegisteredStatusEnum[] array = values();
            for (MagneticDetectorRegisteredStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
