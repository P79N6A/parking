package com.zhuyitech.parking.ucc.enums;

/**
 * @author AkeemSuper
 * @date 2018/5/15 0015
 */
public enum UserAccountTypeEnum {
    PHONE(1, "手机号"),
    WX(2, "微信"),
    ALI(3, "支付宝");

    /**
     * 值
     */
    private int value;

    /**
     * 注释
     */
    private String comment;

    public int getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    UserAccountTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserAccountTypeEnum parse(Integer value) {
        if (value != null) {
            UserAccountTypeEnum[] array = values();
            for (UserAccountTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
