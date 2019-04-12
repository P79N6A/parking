package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 停车场等级枚举
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ParkingLevelEnum {

    /**
     * 0表示不区分停车场等级，1表示一级，2表示二级，3表示三级
     */
    EQUALLY(0, "不区分等级"),

    ONE_LEVEL(1, "一级"),

    TWO_LEVEL(2, "二级"),

    THREE_LEVEL(3, "三级"),
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
    public static ParkingLevelEnum parse(Integer value) {
        if (value != null) {
            ParkingLevelEnum[] array = values();
            for (ParkingLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
