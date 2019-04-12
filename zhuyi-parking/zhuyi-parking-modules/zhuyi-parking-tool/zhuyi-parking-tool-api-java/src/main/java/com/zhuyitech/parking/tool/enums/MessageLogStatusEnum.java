package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 第三方调用接口日志状态枚举
 *
 * @author AkeemSuper
 * @date 2018/4/10 0010
 */
public enum MessageLogStatusEnum {

    SUCCESS(0, "接口调用成功"),
    FAILED(1, "接口调用失败");

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

    MessageLogStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static MessageLogStatusEnum parse(Integer value) {
        if (value != null) {
            MessageLogStatusEnum[] array = values();
            for (MessageLogStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
