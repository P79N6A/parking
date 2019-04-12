package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 停车订单状态
 *
 * @Date: 2018/6/26
 * @author: wh
 */
@NoArgsConstructor
@AllArgsConstructor
public enum ParkingOrderStatusEnum {

    /**
     * 全部
     */
    ALL(0, "全部订单状态"),

    /**
     * 正常停车订单
     */
    /**
     * 入场停车中
     */
    PARKING(1, "入场停车中"),

    /**
     * 已支付未出场
     */
    PAYSUCCESS_PARKING(2, "已支付未出场"),

    /**
     * 结算已出场
     */
    PAYSUCCESS_OUTING(3, "结算已出场"),

    /**
     * 出场未支付
     */
    OUTING_CREATED(4, "出场未支付"),

    /**
     * 出场已结算
     */
    OUTING_PAYSUCCESS(5, "出场已结算");

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
    public static ParkingOrderStatusEnum parse(Integer value) {
        if (value != null) {
            ParkingOrderStatusEnum[] array = values();
            for (ParkingOrderStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
