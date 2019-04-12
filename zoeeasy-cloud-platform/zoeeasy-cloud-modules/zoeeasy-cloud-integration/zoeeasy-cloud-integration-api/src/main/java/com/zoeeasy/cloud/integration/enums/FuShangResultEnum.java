package com.zoeeasy.cloud.integration.enums;

/**
 * 富尚占用状态枚举
 *
 * @Date: 2018/12/5
 * @author: lhj
 */
public enum FuShangResultEnum {

    OCCUPATION(1, "占用"),

    LEISURE(0, "空闲");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    FuShangResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static FuShangResultEnum parse(Integer value) {
        if (value != null) {
            FuShangResultEnum[] array = values();
            for (FuShangResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
