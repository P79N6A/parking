package com.zhuyitech.parking.ucc.enums;

/**
 * @author AkeemSuper
 * @date 2018/5/15 0015
 */
public enum UserLoginTypeEnum {
    LOGIN_PASSWORD(1, "密码登录"),
    LOGIN_VERIFY_CODE(2, "验证码登录"),
    LOGIN_WX(3, "微信登录"),
    LOGIN_ALI(4, "支付宝登陆");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    UserLoginTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserLoginTypeEnum parse(Integer value) {
        if (value != null) {
            UserLoginTypeEnum[] array = values();
            for (UserLoginTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
