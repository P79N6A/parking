package com.zoeeasy.cloud.tool.enums;

/**
 * 标记地区类型
 *
 * @author wangfeng
 * @date 2018/11/17 11:32
 **/
public enum RegionTypeEnum {

    NORMAL(0, "通常区域"),
    MUNICIPALITY(1, "直辖市"),
    COUNTY_LEVEL_CITY(2, "县级市"),
    SPECIAL_PREFECTURE_LEVEL_CITY(3, "特殊地级市"),
    TRANSITION(4, "过渡级别");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    RegionTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static RegionTypeEnum parse(Integer value) {
        if (value != null) {
            RegionTypeEnum[] array = values();
            for (RegionTypeEnum each : array) {
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
