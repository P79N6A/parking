package com.zhuyitech.parking.ucc.enums;


/**
 * 车辆认证认证状态枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum CarAuthStatusEnum {

    /**
     * 未认证
     */
    AUTHENTICATION_NO(0, "未认证"),
    /**
     * 认证中
     */
    AUTHENTICATION_PENDING(1, "认证中"),
    /**
     * 已认证
     */
    AUTHENTICATED(2, "已认证"),
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

    CarAuthStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static CarAuthStatusEnum parse(Integer value) {
        if (value != null) {
            CarAuthStatusEnum[] array = values();
            for (CarAuthStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}