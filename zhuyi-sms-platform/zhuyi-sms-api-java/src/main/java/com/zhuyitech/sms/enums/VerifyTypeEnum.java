package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 验证码类型枚举
 *
 * @author walkman
 */
public enum VerifyTypeEnum {

    /**
     * 1, 注册
     */
    REGISTER(1, "注册"),

    /**
     * 2, 登录
     */
    LOGIN(2, "登录"),

    /**
     * 3, 找回密码
     */
    FORGETPASSWORD(3, "找回密码"),

    /**
     * 4, 找回密码
     */
    SETPASSWORD(4, "密码设置"),

    /**
     * 5, 支付密码
     */
    PAYPASSWORD(5, "支付密码");

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

    VerifyTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static VerifyTypeEnum parse(Integer value) {
        if (value != null) {
            VerifyTypeEnum[] array = values();
            for (VerifyTypeEnum each : array) {
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
