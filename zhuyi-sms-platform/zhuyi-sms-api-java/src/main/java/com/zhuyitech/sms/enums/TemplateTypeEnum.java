package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 短信模板类型枚举
 *
 * @author walkman
 */
public enum TemplateTypeEnum {

    /**
     * 1, 验证码
     */
    VERIFYCODE(1, "验证码"),

    /**
     * 2, 通知
     */
    NOTIFY(2, "通知"),
    ;

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

    TemplateTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TemplateTypeEnum parse(Integer value) {
        if (value != null) {
            TemplateTypeEnum[] array = values();
            for (TemplateTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

    public boolean isEqual(Integer v) {
        return v != null && v == this.value;
    }
}
