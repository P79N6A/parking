package com.zhuyitech.parking.tool.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 通知消息业务类型枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum NotificationMessageTypeEnum {

    /**
     * 停车新账单
     */
    NEW_PARKING_ORDER(1, "停车新账单"),

    /**
     * 账单支付成功
     */
    ORDER_PAY_SUCCESS(2, "账单支付成功"),
    ;

    /**
     * 代码
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
    public static NotificationMessageTypeEnum parse(Integer value) {
        if (value != null) {
            NotificationMessageTypeEnum[] array = values();
            for (NotificationMessageTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
