package com.zhuyitech.parking.common.enums;


/**
 * 可用状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
public enum EnableStatusEnum {

    /**
     * 可用
     */
    ENABLED(1, "可用"),

    /**
     * 不可用
     */
    DISABLED(2, "不可用"),;

    /**
     * 值
     */
    private int value;

    /**
     * 注释
     */
    private String comment;

    public int getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    EnableStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static EnableStatusEnum parse(Integer value) {
        if (value != null) {
            EnableStatusEnum[] array = values();
            for (EnableStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}

