package com.zoeeasy.cloud.notify.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 通知消息类型枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
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
    public static NotificationTypeEnum parse(Integer value) {
        if (value != null) {
            NotificationTypeEnum[] array = values();
            for (NotificationTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}

