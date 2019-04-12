package com.zoeeasy.cloud.notify.enums;

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
    PUSH_INSPECT_INTO_PASS("5", "入车巡检推送"),
    PUSH_INSPECT_OUT_PASS("6", "出车巡检推送"),
    ;

    /**
     * 代码
     */
    private String code;

    /**
     * 注释
     */
    private String comment;

    public String getCode() {
        return code;
    }

    public String getComment() {
        return this.comment;
    }

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
