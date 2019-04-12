package com.zhuyitech.parking.pms.enums;

import lombok.Getter;

/**
 * 停车图像类型枚举
 *
 * @author walkman
 * @date 2017-12-10
 */
public enum ParkingImageTypeEnum {

    /**
     * 0 停车场信息 1级为过车，2级为停车
     */
    PARKINGINFO(0, "停车场"),

    PASSING(1, "过车"),

    PARKING(2, "停车"),
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

    ParkingImageTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParkingImageTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingImageTypeEnum[] array = values();
            for (ParkingImageTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
