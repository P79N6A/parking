package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 地区层级枚举
 *
 * @author walkman
 */
public enum RegionLevelEnum {

    COUNTRY(0, "国家"),
    PROVINCE(1, "省"),
    CITY(2, "市"),
    COUNTY(3, "区县");

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

    RegionLevelEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static RegionLevelEnum parse(Integer value) {
        if (value != null) {
            RegionLevelEnum[] array = values();
            for (RegionLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
