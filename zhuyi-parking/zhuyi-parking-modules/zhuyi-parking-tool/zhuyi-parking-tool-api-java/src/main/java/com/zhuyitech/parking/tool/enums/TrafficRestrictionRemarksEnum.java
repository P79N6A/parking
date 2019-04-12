package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 限行类型枚举
 *
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
public enum TrafficRestrictionRemarksEnum {
    LAST_NUMBER(-1, "最后一位数字"),
    ZERO_TAIL_NUMBER(0, "尾号字母按0处理"),
    FOUR_TAIL_NUMBER(4, "尾号字母按4处理");

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

    TrafficRestrictionRemarksEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TrafficRestrictionRemarksEnum parse(Integer value) {
        if (value != null) {
            TrafficRestrictionRemarksEnum[] array = values();
            for (TrafficRestrictionRemarksEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
