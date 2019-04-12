package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 过车数据处理状态枚举
 *
 * @Date: 2018/8/1
 * @author: walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum VehicleProcessStatusEnum {

    /**
     *
     */
    PROCESS_NOT(1, "未处理"),

    PROCESSING(2, "处理中"),

    PROCESS_SUCCESS(3, "已处理"),

    PROCESS_FAIL(4, "处理失败"),
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
    public static VehicleProcessStatusEnum parse(Integer value) {
        if (value != null) {
            VehicleProcessStatusEnum[] array = values();
            for (VehicleProcessStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}