package com.zhuyitech.parking.ucc.enums;

/**
 * 性别
 *
 * @author zwq
 * @date 2018-01-10
 */
public enum UserGenderEnum {

    /**
     * 1, 男
     */
    MAN(1, "男"),

    /**
     * 2, 女
     */
    WOMEN(2, "女"),;

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

    UserGenderEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserGenderEnum parse(Integer value) {
        if (value != null) {
            UserGenderEnum[] array = values();
            for (UserGenderEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
