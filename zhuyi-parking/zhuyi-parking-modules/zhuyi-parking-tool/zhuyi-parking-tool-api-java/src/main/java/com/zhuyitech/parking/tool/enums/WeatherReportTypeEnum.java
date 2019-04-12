package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * @author zwq
 * @date 2018/4/13
 */
public enum WeatherReportTypeEnum {

    LIVES(0, "实况"),

    FORECAST(1, "预报"),
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

    WeatherReportTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static WeatherReportTypeEnum parse(Integer value) {
        if (value != null) {
            WeatherReportTypeEnum[] array = values();
            for (WeatherReportTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
