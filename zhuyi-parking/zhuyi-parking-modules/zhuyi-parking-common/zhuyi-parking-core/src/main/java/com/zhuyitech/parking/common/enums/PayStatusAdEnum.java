package com.zhuyitech.parking.common.enums;


/**
 * 支付状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
public enum PayStatusAdEnum {

    /**
     * 全部
     */
    ALL(1, "全部支付状态"),

    /**
     * 未支付
     */
    PAY_WAITING(2, "未支付"),

    /**
     * 已退款
     */
    PAY_SUCCESS(3, "已支付"),

    /**
     * 已退款
     */
    REFUND(4, "已退款"),;

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

    PayStatusAdEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static PayStatusAdEnum parse(Integer value) {
        if (value != null) {
            PayStatusAdEnum[] array = values();
            for (PayStatusAdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
