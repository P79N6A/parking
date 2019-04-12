package com.zhuyitech.parking.ucc.enums;

/**
 * @Date: 2018/1/12
 * @author: yuzhicheng
 */
public enum UserCarAuthStatusEnum {

    /**
     * 未认证
     */
    CAR_AUTH_UNAUTH(0, "未认证"),

    /**
     * 认证中
     */
    CAR_AUTH_PENDING(1, "认证中"),

    /**
     * 已认证
     */
    CAR_AUTH_ALREADY(2, "已认证"),

    /**
     * 认证不通过
     */
    AUTHENTICATION_REJECT(3, "认证不通过");

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

    UserCarAuthStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserCarAuthStatusEnum parse(Integer value) {
        if (value != null) {
            UserCarAuthStatusEnum[] array = values();
            for (UserCarAuthStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

}
