package com.zhuyitech.parking.ucc.enums;


/**
 * 实名认证审核意见枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UserAuthOpinionEnum {

    /**
     * 通过
     */
    APPROVED(1, "通过"),
    /**
     * 拒绝
     */
    REJECTED(2, "拒绝");
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

    UserAuthOpinionEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserAuthOpinionEnum parse(Integer value) {
        if (value != null) {
            UserAuthOpinionEnum[] array = values();
            for (UserAuthOpinionEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
