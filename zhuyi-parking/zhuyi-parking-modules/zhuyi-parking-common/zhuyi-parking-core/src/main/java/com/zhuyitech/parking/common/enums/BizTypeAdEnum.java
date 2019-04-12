package com.zhuyitech.parking.common.enums;


/**
 * 订单类型枚举
 *
 * @author zwq
 * @date 2018-06-26
 */
public enum BizTypeAdEnum {

    /**
     * 全部
     */
    ALL(1, "全部"),

    /**
     * 充值
     */
    RECHARGE(2, "充值"),

    /**
     * 支付账单
     */
    PAYMENT(3, "支付账单"),;

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

    BizTypeAdEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static BizTypeAdEnum parse(Integer value) {
        if (value != null) {
            BizTypeAdEnum[] array = values();
            for (BizTypeAdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
