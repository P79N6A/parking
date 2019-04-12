package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 过车异常类型枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PassingExceptionTypeEnum {

    /**
     * 未知
     */
    NOT_EXCEPTION(0, "正常过车"),

    /**
     * 入车
     */
    PARKING_NOT_FOUND(1, "停车场不存在"),

    /**
     * 泊位不存在
     */
    PARKING_LOT_NOT_FOUND(2, "泊位不存在"),

    /**
     * 车库不存在
     */
    PARKING_GARAGE_NOT_FOUND(3, "车库不存在"),

    /**
     * 出入口不存在
     */
    PARKING_GATE_NOT_FOUND(4, "出入口不存在"),

    /**
     * 车道不存在
     */
    PARKING_LANE_NOT_FOUND(5, "车道不存在"),

    /**
     * 连续入车
     */
    PASSING_LESS_THAN_WINDOW(6, "连续入车"),

    /**
     * 入车记录不存在
     */
    PASSING_ENTER_NOT_FOUNT(7, "入车记录不存在"),

    VEHICLE_NOT_PARKING(8, "车辆不在场"),

    NOT_INSPECT_PASS(9, "未巡检过车"),

    PARK_RECORD_NOT_FOUNT(10, "停车记录不存在"),
    PLATE_NUMBER_NOT_STANDARD(11, "车牌不符合规范"),
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

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PassingExceptionTypeEnum parse(Integer value) {
        if (value != null) {
            PassingExceptionTypeEnum[] array = values();
            for (PassingExceptionTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
