package com.zhuyitech.parking.ucc.enums;

/**
 * 通用状态枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum CommonStatusEnum {

    /**
     * 可用
     */
    DISABLED(0, "不可用"),

    /**
     * 可用
     */
    ENABLED(1, "可用"),;
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

    CommonStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static CommonStatusEnum parse(Integer value) {
        if (value != null) {
            CommonStatusEnum[] array = values();
            for (CommonStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
