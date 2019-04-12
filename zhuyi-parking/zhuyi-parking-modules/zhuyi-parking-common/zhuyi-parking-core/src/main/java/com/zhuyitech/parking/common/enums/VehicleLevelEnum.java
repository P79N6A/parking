package com.zhuyitech.parking.common.enums;


/**
 * 车辆等级类型枚举
 *
 * @author walkman
 * @date 2017-12-10
 */
public enum VehicleLevelEnum {

    /**
     * 1为小型汽车，2级为大型汽车，0 其他
     */
    OTHER(0, "其他"),

    SMALL(1, "小型汽车"),

    LARGE(2, "大型汽车"),;

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    VehicleLevelEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static VehicleLevelEnum parse(Integer value) {
        if (value != null) {
            VehicleLevelEnum[] array = values();
            for (VehicleLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}