package com.zhuyitech.parking.ucc.enums;

/**
 * 密码类型枚举
 *
 * @author walkman
 * @date 2017-02-10
 */
public enum PasswordTypeEnum {

    /**
     * 登录
     */
    ACCESS(1, "登录"),
    /**
     * 交易
     */
    TRADE(2, "交易");

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

    PasswordTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PasswordTypeEnum parse(Integer value) {
        if (value != null) {
            PasswordTypeEnum[] array = values();
            for (PasswordTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
