package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * @author AkeemSuper
 * @date 2018/4/12 0012
 */
public enum ProsAndConsEnum {
    PROS("face", "正面"),
    CONS("back", "背面"),
    ;

    /**
     * 值
     */
    @Getter
    private String value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    ProsAndConsEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ProsAndConsEnum parse(String value) {
        if (value != null) {
            ProsAndConsEnum[] array = values();
            for (ProsAndConsEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
