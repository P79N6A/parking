package com.zhuyitech.parking.pms.enums;

import lombok.Getter;

/**
 * 停车场过车类型枚举
 *
 * @author walkman
 */
public enum PassingTypeEnum {

    ENTRY(1, "入车"),

    EXIT(2, "出车");

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

    PassingTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PassingTypeEnum parse(Integer value) {
        if (value != null) {
            PassingTypeEnum[] array = values();
            for (PassingTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
