package com.zoeeasy.cloud.charge.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 假期类型枚举
 *
 * @author AkeemSuper
 */
@AllArgsConstructor
@NoArgsConstructor
public enum HolidayTypeEnum {

    /**
     * 工作日，双休日，小长假，长假
     */
    ALL(0, "不区分"),
    WORK_DAY(1, "工作日"),
    WEEKEND(2, "双休日"),
    MINOR_VOCATION(3, "小长假"),
    LONG_VOCATION(4, "长假");

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
    public static HolidayTypeEnum parse(Integer value) {
        if (value != null) {
            HolidayTypeEnum[] array = values();
            for (HolidayTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
