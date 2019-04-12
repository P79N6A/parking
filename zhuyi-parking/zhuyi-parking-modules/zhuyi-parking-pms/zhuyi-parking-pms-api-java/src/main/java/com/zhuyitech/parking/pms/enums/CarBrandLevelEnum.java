package com.zhuyitech.parking.pms.enums;


import lombok.Getter;

/**
 * 车型层级枚举
 *
 * @author walkman
 * @date 2017-12-10
 */
public enum CarBrandLevelEnum {

    /**
     * 1级为车品牌，2级为车品牌子公司，3级为车型，4级为具体的车款
     */
    CAR_BRAND(1, "车品牌"),

    CAR_SUB_BRAND(2, "车品牌子公司"),

    CAR_MODEL(3, "车型"),

    CAR_TYPE(4, "车款");

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

    CarBrandLevelEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static CarBrandLevelEnum parse(Integer value) {
        if (value != null) {
            CarBrandLevelEnum[] array = values();
            for (CarBrandLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
