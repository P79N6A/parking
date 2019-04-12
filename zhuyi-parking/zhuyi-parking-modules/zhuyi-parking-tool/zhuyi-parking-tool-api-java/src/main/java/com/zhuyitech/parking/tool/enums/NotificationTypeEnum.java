package com.zhuyitech.parking.tool.enums;


import lombok.Getter;

/**
 * 通知消息类型枚举
 *
 * @author walkman
 */
public enum NotificationTypeEnum {

    /**
     *
     */
    ALERT(1, "通知"),

    /**
     *
     */
    ACTIVITY(2, "活动"),

    /**
     *
     */
    FEED(3, "互动"),

    /**
     *
     */
    NEW(4, "快报");

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

    NotificationTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static NotificationTypeEnum parse(Integer value) {
        if (value != null) {
            NotificationTypeEnum[] array = values();
            for (NotificationTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}

