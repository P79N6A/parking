package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 汽油类型枚举
 *
 * @author AkeemSuper
 * @date 2018/8/2 0002
 */
public enum OilPriceTypeEnum {

    REAL_PRICE(1, "实价"),

    AVG_PRICE(2, "当月均价");

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

    OilPriceTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static OilPriceTypeEnum parse(Integer value) {
        if (value != null) {
            OilPriceTypeEnum[] array = values();
            for (OilPriceTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
