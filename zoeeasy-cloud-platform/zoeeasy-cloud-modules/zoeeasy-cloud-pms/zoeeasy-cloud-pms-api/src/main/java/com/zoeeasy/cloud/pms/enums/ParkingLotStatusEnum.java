package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 车位状态枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingLotStatusEnum {

    /**
     * 0  空闲
     */
    FREE(0, "空闲"),

    /**
     * 1 占用
     */
    USED(1, "占用"),

    /**
     * 2 未知
     */
    DAMAGED(2, "未知");

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


    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParkingLotStatusEnum parse(Integer value) {
        if (value != null) {
            ParkingLotStatusEnum[] array = values();
            for (ParkingLotStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }


}

