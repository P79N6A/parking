package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 限流枚举
 */
public enum LimitRuleTypeEnum {

    /**
     * 常规限流：
     * 60s内获取验证码有效，当日发送验证码次数不超过20次。连续五次验证失败后需要2小时后再试
     */
    COMMON(1, "常规限流");
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

    LimitRuleTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static LimitRuleTypeEnum parse(Integer value) {
        if (value != null) {
            LimitRuleTypeEnum[] array = values();
            for (LimitRuleTypeEnum each : array) {
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
