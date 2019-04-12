package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum VisitTypeEnum {

    VISIT_VEHICLE_FIXED(1, "固定车访客车"),

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
    public static VisitTypeEnum parse(Integer value) {
        if (value != null) {
            VisitTypeEnum[] array = values();
            for (VisitTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
