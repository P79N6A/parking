package com.zhuyitech.parking.ucc.enums;


/**
 * 用户实名认证状态枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UserAuthStatusEnum {

    /**
     * 未认证
     */
    AUTHENTICATION_NO(0, "未认证"),
    /**
     * 认证中
     */
    AUTHENTICATION_PENDING(1, "认证中"),
    /**
     * 已实名
     */
    AUTHENTICATED(2, "已认证"),
    /**
     * 认证不通过
     */
    AUTHENTICATION_REJECT(3, "认证失败");

    /**
     * 值
     */
    private Integer value;

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

    UserAuthStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserAuthStatusEnum parse(Integer value) {
        if (value != null) {
            UserAuthStatusEnum[] array = values();
            for (UserAuthStatusEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}
