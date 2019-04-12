package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 限行车辆类型枚举
 *
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
public enum TrafficRestrictionStatusEnum {

    UN_TRAFFIC_LIMIT(1, "无限行"),
    UN_KNOW(2, "未知"),
    TRAFFIC_LIMIT(3, "限行");

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

    TrafficRestrictionStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TrafficRestrictionStatusEnum parse(Integer value) {
        if (value != null) {
            TrafficRestrictionStatusEnum[] array = values();
            for (TrafficRestrictionStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
