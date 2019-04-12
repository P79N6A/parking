package com.zhuyitech.parking.tool.enums;


import lombok.Getter;

/**
 * 推送消息类型枚举
 *
 * @author walkman
 */
public enum PushMessageType {

    /**
     *
     */
    NEW_ORDER("1", "新停车账单"),
    ORDER_PAY_SUCCESS("2", "账单支付成功"),
    TRAFFIC_LIMIT_HINT("3", "限行提醒"),
    PARKING_ENTER("4", "停车入场"),
    ;

    /**
     * 代码
     */
    @Getter
    private String code;

    /**
     * 注释
     */
    @Getter
    private String comment;

    PushMessageType(String code, String comment) {
        this.code = code;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param code 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PushMessageType parse(String code) {
        if (code != null) {
            PushMessageType[] array = values();
            for (PushMessageType each : array) {
                if (code.equals(each.getCode())) {
                    return each;
                }
            }
        }
        return null;
    }
}
