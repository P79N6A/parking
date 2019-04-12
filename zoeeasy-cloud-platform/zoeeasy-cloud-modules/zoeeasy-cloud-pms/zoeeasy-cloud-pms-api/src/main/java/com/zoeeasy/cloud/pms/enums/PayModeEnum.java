package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/27.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayModeEnum {

    PARKING_CARD(1, "停车卡缴费"),
    SUPPLIES(2, "物料缴费"),
    CENTER_PAYMENT(3, "中央缴费机"),

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
    public static PayModeEnum parse(Integer value) {
        if (value != null) {
            PayModeEnum[] array = values();
            for (PayModeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应的枚举
     *
     * @param comment
     * @return
     */
    public static PayModeEnum getValue(String comment) {
        if (comment != null) {
            PayModeEnum[] array = values();
            for (PayModeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
