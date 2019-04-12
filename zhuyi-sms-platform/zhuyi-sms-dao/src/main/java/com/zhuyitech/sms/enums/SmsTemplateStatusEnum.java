package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 活动状态:1,enabled,可用;2,disabled,不可用;3,removed,已删除;
 */
public enum SmsTemplateStatusEnum {

    /**
     * 1, 可用
     */
    ENABLED(1, "可用"),
    /**
     * 2, 不可用
     */
    DISABLED(2, "不可用"),
    /**
     * 3, 已删除
     */
    REMOVED(3, "已删除");
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

    SmsTemplateStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static SmsTemplateStatusEnum parse(Integer value) {
        if (value != null) {
            SmsTemplateStatusEnum[] array = values();
            for (SmsTemplateStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
