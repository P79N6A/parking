package com.zhuyitech.parking.pms.enums;


import lombok.Getter;

/**
 * 停车场类型(1为地面，2为地下，3为路边)
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum ParkingTypeEnum {

    /**
     * 1 地面
     */
    GROUND("1", "地面"),

    /**
     * 2 地下
     */
    ROADWAY("2", "地下"),

    /**
     * 3为路面停车场
     */
    UNDERGROUND("3", "路边"),
    ;

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

    ParkingTypeEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParkingTypeEnum parse(String value) {
        if (value != null) {
            ParkingTypeEnum[] array = values();
            for (ParkingTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
