package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 加油站状态枚举
 *
 * @author zwq
 */
public enum GasStatusEnum {

    /**
     * 加油站
     */
    GAS_YOU(1, "加油站"),
    /**
     * 加气站
     */
    GAS_QI(2, "加气站"),
    /**
     * 加气站
     */
    GAS_DIAN(3, "充电桩");

    /**
     * 值
     */
    @Getter
    private int value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    GasStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static GasStatusEnum parse(Integer value) {
        if (value != null) {
            GasStatusEnum[] array = values();
            for (GasStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

}
