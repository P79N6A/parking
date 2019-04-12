package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 限行车辆类型枚举
 *
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
public enum TrafficRestrictionCityCarTypeEnum {
    CAR(0, "小客车"),
    TRUCKS(1, "货车");

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

    TrafficRestrictionCityCarTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TrafficRestrictionCityCarTypeEnum parse(Integer value) {
        if (value != null) {
            TrafficRestrictionCityCarTypeEnum[] array = values();
            for (TrafficRestrictionCityCarTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
