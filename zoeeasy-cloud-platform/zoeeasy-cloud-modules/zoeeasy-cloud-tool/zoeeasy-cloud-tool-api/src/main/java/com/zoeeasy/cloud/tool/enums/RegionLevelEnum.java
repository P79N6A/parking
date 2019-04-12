package com.zoeeasy.cloud.tool.enums;

/**
 * 地区层级枚举
 *
 * @author walkman
 */
public enum RegionLevelEnum {

    COUNTRY(0, "国家"),
    PROVINCE(1, "省"),
    CITY(2, "市"),
    COUNTY(3, "区县"),
    CUSTOM(4, "自定义");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
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

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
