package com.zhuyitech.parking.pms.enums;


import lombok.Getter;

/**
 * 停车状态枚举
 *
 * @author walkman
 */
public enum ParkingStatusEnum {

    /**
     * 在停中
     */
    PARKING(1, "在停中"),

    /**
     * 已出场
     */
    OUTING(2, "已出场");

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

    ParkingStatusEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParkingStatusEnum parse(Integer value) {
        if (value != null) {
            ParkingStatusEnum[] array = values();
            for (ParkingStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}

