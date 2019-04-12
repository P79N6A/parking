package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 过车数据来源枚举
 *
 * @author walkman
 * @date 2018/08/02
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PassingVehicleDataSourceEnum {

    /**
     * 海康威视
     */
    UNKNOWN(0, "未知"),

    /**
     * 海康威视
     */
    HIKVISION(1, "海康威视"),

    /**
     * 支付宝
     */
    ALIPAY(2, "支付宝"),

    /**
     * 任意停车
     */
    ANY_PARKING(3, "任意停车"),

    /**
     * 人工添加
     */
    MANUAL(4, "人工添加"),

    /**
     * 富尚
     */
    FUSHANG(5, "富尚"),
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
    public static PassingVehicleDataSourceEnum parse(Integer value) {
        if (value != null) {
            PassingVehicleDataSourceEnum[] array = values();
            for (PassingVehicleDataSourceEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }
}
