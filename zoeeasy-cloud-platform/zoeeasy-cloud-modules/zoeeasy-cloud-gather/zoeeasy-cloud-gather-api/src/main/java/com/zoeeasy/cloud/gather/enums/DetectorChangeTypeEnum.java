package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum DetectorChangeTypeEnum {

    CAR_COMEIN(1, "车辆到达"),

    CAR_COMEOUT(2, "车辆离开"),;

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
    public static DetectorChangeTypeEnum parse(Integer value) {
        if (value != null) {
            DetectorChangeTypeEnum[] array = values();
            for (DetectorChangeTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
