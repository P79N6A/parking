package com.zhuyitech.parking.common.enums;


/**
 * 支付状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
public enum PayStateEnum {

    /**
     * 不区分
     */
    NONE(0, "不区分"),

    /**
     * 未支付
     */
    NOT_PAYED(1, "未支付"),

    /**
     * 已支付
     */
    PAYED(2, "已支付"),;

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

    PayStateEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PayStateEnum parse(Integer value) {
        if (value != null) {
            PayStateEnum[] array = values();
            for (PayStateEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
