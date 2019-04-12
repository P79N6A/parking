package com.zhuyitech.parking.ucc.enums;

/**
 * 用户车辆认证意见枚举
 *
 * @Date: 2018/1/12
 * @author: yuzhihceng
 */
public enum UserCarAuthOpinionEnum {

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

    UserCarAuthOpinionEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserCarAuthOpinionEnum parse(Integer value) {
        if (value != null) {
            UserCarAuthOpinionEnum[] array = values();
            for (UserCarAuthOpinionEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
