package com.zoeeasy.cloud.pds.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 地磁检测器状态枚举
 *
 * @author lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MagneticDetectorStatusEnum {
    /**
     * -1 未知
     */
    OTHER(-1, "未知"),

    /**
     * 0正常
     */
    NORMAL(0, "正常"),

    /**
     * 4 失联
     */
    GO_MISSING(4, "失联");

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
    public static MagneticDetectorStatusEnum parse(Integer value) {
        if (value != null) {
            MagneticDetectorStatusEnum[] array = values();
            for (MagneticDetectorStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
