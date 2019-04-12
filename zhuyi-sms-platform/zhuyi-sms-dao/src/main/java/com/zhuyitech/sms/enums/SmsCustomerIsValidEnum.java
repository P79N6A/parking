package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 是否有效:1,yes,有效;2,no,无效;
 */
public enum SmsCustomerIsValidEnum {
    /**
     * 1, 有效
     */
    YES(1, "有效"),
    /**
     * 2, 无效
     */
    NO(2, "无效");
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

    SmsCustomerIsValidEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static SmsCustomerIsValidEnum parse(Integer value) {
        if (value != null) {
            SmsCustomerIsValidEnum[] array = values();
            for (SmsCustomerIsValidEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
