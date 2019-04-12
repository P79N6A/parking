package com.zoeeasy.cloud.message.enums;

/**
 * MQ消息发送状态枚举
 *
 * @author walkman
 * @since 2018/1/26
 */
public enum MessagingSendStatusEnum {

    /**
     * 发送中
     */
    SENDING(1, "发送中"),

    /**
     * 确认中
     */
    CONFIRMING(2, "确认中"),

    /**
     * 已确认
     */
    CONFIRMED(3, "已确认"),

    /**
     * 已取消
     */
    CANCEL(4, "已取消"),
    ;

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    MessagingSendStatusEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static MessagingSendStatusEnum parse(Integer value) {
        if (value != null) {
            MessagingSendStatusEnum[] array = values();
            for (MessagingSendStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
