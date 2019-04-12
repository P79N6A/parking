package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 艾佳上报数据类型
 *
 * @Date: 2018/8/22
 * @author: wh
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AirJoyReportDataTypeEnum {
    BUSINESS("1", "业务数据"),
    HEARTBEAT("2", "心跳数据"),
    ;
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
    public static AirJoyReportDataTypeEnum parse(String value) {
        if (value != null) {
            AirJoyReportDataTypeEnum[] array = values();
            for (AirJoyReportDataTypeEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }
}
