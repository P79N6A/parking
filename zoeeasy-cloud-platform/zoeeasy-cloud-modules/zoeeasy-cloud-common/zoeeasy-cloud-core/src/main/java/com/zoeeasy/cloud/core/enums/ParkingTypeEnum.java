package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description: 海康停车场类型枚举
 * @Autor: AkeemSuper
 * @Date:2018/2/2
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingTypeEnum {

    ENCLOSED_GARAGE(1, "封闭式车库"),
    OPEN_GARAGE(2, "路边停车场"),
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
    public static ParkingTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingTypeEnum[] array = values();
            for (ParkingTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
