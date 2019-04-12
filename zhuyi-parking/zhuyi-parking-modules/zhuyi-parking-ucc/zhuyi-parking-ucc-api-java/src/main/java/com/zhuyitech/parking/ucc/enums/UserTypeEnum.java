package com.zhuyitech.parking.ucc.enums;

/**
 * 用户类型枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UserTypeEnum {

    /**
     * 1, 超级管理员
     */
    ADMINISTRATOR(1, "超级管理员"),

    /**
     * 2, 普通管理员
     */
    MANAGER(2, "普通管理员"),

    /**
     * 客户
     */
    CUSTOMER(3, "客户"),

    /**
     * 临时客户-微信公众号无手机号注册用户
     */
    TEMPORARY_CUSTOMER(4, "临时客户");

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

    UserTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserTypeEnum parse(Integer value) {
        if (value != null) {
            UserTypeEnum[] array = values();
            for (UserTypeEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 判断是否是超级管理员
     *
     * @param value
     * @return
     */
    public static boolean isAdministrator(Object value) {
        return value != null && Integer.parseInt(value.toString()) == ADMINISTRATOR.getValue();
    }
}
