package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 限行类型枚举
 *
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
public enum TrafficRestrictionTypeEnum {
    //1:今日 2:明天 3:后天 4:第4天 5:第5天 6:第6天
    TODAY(1, "今日"),
    TOMORROW(2, "明天"),
    THIRD_DAY(3, "后天"),
    FOURTH_DAY(4, "第四天"),
    FIFTH_DAY(5, "第五天"),
    SIXTH_DAY(6, "第六天");

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

    TrafficRestrictionTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TrafficRestrictionTypeEnum parse(Integer value) {
        if (value != null) {
            TrafficRestrictionTypeEnum[] array = values();
            for (TrafficRestrictionTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
