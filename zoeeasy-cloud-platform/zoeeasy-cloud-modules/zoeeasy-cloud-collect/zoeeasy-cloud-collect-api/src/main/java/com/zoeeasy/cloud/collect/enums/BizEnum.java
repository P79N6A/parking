package com.zoeeasy.cloud.collect.enums;

/**
 * @Date: 2019-03-01
 * @author: wf
 */
public enum BizEnum {

    /**
     * 心跳
     */
    HEART_BEAT(1, "heart_beat"),

    /**
     * 注册
     */
    CHECK_KEY(2, "check_key"),

    /**
     * 查询价格
     */
    QUERY_PRICE(3, "query_price"),

    /**
     * 支付通知
     */
    PAYMENT_NOTIFY(4, "payment_notify"),

    /**
     * 远程开闸
     */
    OPEN_STROBE(5, "open_strobe");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    BizEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static BizEnum parse(Integer value) {
        if (value != null) {
            BizEnum[] array = values();
            for (BizEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return value;
    }

    public String getComment() {
        return comment;
    }
}
