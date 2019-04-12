package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 汽油类型枚举
 *
 * @author AkeemSuper
 * @date 2018/8/2 0002
 */
public enum OilTypeEnum {

    B90("b89", "89号汽油"),

    B93("b92", "92号汽油"),

    B97("b95", "95号汽油"),

    B0("b0", "0号汽油");

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

    OilTypeEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static OilTypeEnum parse(String value) {
        if (value != null) {
            OilTypeEnum[] array = values();
            for (OilTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
