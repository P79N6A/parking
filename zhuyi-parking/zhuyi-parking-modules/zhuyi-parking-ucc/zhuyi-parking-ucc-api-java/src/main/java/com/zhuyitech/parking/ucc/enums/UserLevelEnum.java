package com.zhuyitech.parking.ucc.enums;

/**
 * 用户等级枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UserLevelEnum {

    /**
     * 普通用户
     */
    NORMAL(1, "普通用户"),
    /**
     * 铜牌用户
     */
    COPPER(2, "铜牌用户"),
    /**
     * 银牌用户
     */
    SILVER(3, "银牌用户"),
    /**
     * 金牌用户
     */
    GOLDEN(4, "金牌用户");
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

    UserLevelEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserLevelEnum parse(Integer value) {
        if (value != null) {
            UserLevelEnum[] array = values();
            for (UserLevelEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
