package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 海康车辆类型枚举
 *
 * @author walkman
 * @date 2017-12-10
 */
@AllArgsConstructor
@NoArgsConstructor
public enum VehicleLevelEnum {

    /**
     * 1为小型汽车，2级为大型汽车，0 其他
     */
    OTHER(0, "其他"),

    SMALL(1, "小型汽车"),

    LARGE(2, "大型汽车"),
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
    public static VehicleLevelEnum parse(Integer value) {
        if (value != null) {
            VehicleLevelEnum[] array = values();
            for (VehicleLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}