package com.zoeeasy.cloud.pds.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 地磁误报处理记录返回结果枚举
 *
 * @author lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum MagneticErrorReportAbnormityEnum {
    /**
     * 0-地磁误报进车（进车时）
     */
    MISINFORMATION_INTO(0, "地磁误报进车（进车时）"),

    /**
     * 1-地磁误报出车（出车时）
     */
    MISINFORMATION_OUT(1, "地磁误报出车（出车时）"),

    /**
     * 2-连续上报
     */
    CONTINUOUS_REPORTED(2, "连续上报");

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
    public static MagneticErrorReportAbnormityEnum parse(Integer value) {
        if (value != null) {
            MagneticErrorReportAbnormityEnum[] array = values();
            for (MagneticErrorReportAbnormityEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
