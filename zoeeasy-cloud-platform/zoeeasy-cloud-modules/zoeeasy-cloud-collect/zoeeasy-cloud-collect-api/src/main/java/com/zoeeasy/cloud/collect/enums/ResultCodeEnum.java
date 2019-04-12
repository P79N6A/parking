package com.zoeeasy.cloud.collect.enums;

/**
 * @Date: 2019/1/13
 * @author: wh
 */
public enum ResultCodeEnum {

    /**
     * 成功
     */
    SUCCESS(0, "成功"),

    /**
     * 失败
     */
    FAILED(1, "失败");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    ResultCodeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ResultCodeEnum parse(Integer value) {
        if (value != null) {
            ResultCodeEnum[] array = values();
            for (ResultCodeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }
}
