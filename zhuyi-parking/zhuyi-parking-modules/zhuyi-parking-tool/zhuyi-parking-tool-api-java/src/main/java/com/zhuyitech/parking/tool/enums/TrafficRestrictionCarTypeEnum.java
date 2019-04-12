package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 限行车辆类型枚举
 *
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
public enum TrafficRestrictionCarTypeEnum {

    LOCAL_CAR(1, "本地小客车"),
    LOCAL_TRUCKS(2, "本地货车"),
    FOREIGN_TRUCKS(3, "外地货车"),
    FOREIGN_CAR(4, "外地小客车");

    /**
     * 值
     */
    @Getter
    private int value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    TrafficRestrictionCarTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TrafficRestrictionCarTypeEnum parse(Integer value) {
        if (value != null) {
            TrafficRestrictionCarTypeEnum[] array = values();
            for (TrafficRestrictionCarTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
