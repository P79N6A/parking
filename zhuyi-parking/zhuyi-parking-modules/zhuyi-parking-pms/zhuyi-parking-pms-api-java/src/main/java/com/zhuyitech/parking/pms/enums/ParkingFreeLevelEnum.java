package com.zhuyitech.parking.pms.enums;

import lombok.Getter;

/**
 * 停车场空闲率等级
 *
 * @author walkman
 * @date 2017-12-10
 */
public enum ParkingFreeLevelEnum {

    /**
     * 1级为绿色，2级为黄色，3级为红色
     */
    GREEN(1, "绿色"),

    YELLOW(2, "黄色"),

    RED(3, "红色"),
    ;

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

    ParkingFreeLevelEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParkingFreeLevelEnum parse(Integer value) {
        if (value != null) {
            ParkingFreeLevelEnum[] array = values();
            for (ParkingFreeLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
