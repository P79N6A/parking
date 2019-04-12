package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 预约订单状态枚举
 *
 * @author walkman
 * @date 2018-04-01
 */
@AllArgsConstructor
@NoArgsConstructor
public enum AppointStatusEnum {

    /**
     * 已创建
     */
    CREATED(1, "已创建"),

    /**
     * 预定成功,等待入场
     */
    SUCCESS(2, "预定成功"),

    /**
     * 已入场
     */
    ENTERED(3, "已入场"),

    /**
     * 已出场
     */
    EXITED(4, "已出场"),

    /**
     * 预定失败
     */
    FAILED(5, "预定失败"),

    /**
     * 取消中
     */
    CANCELING(6, "取消中"),

    /**
     * 已取消
     */
    CANCELED(7, "已取消"),

    /**
     * 取消失败
     */
    CANCEL_FAILED(8, "取消失败"),
    ;

    @Getter
    private Integer value;

    @Getter
    private String message;

    public static AppointStatusEnum parse(int code) {
        AppointStatusEnum[] codes = values();
        for (AppointStatusEnum each : codes) {
            if (each.value == code) {
                return each;
            }
        }
        return null;
    }

}
