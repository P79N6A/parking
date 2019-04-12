package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 订单类型
 *
 * @Date: 2018/6/29
 * @author: wh
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ParkingOrderTypeEnum {

    /**
     * 全部
     */
    ALL(0, "全部订单类型"),
    /**
     * 正常停车订单
     */
    NORMAL_PARKING(1, "正常停车订单"),

    /**
     * 预约停车订单
     */
    APPOINT_PARKING(2, "预约停车订单"),

    /**
     * 限时免费订单
     */
    LIMIT_PARKING(3, "限时免费订单");

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
    public static ParkingOrderTypeEnum parse(Integer value) {
        if (value != null) {
            ParkingOrderTypeEnum[] array = values();
            for (ParkingOrderTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
