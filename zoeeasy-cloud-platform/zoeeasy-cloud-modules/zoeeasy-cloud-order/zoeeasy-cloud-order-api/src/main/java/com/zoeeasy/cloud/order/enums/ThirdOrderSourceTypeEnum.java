package com.zoeeasy.cloud.order.enums;

import lombok.Getter;


/**
 * 第三方平台停车账单数据源类型
 *
 * @author walkman
 * @since 2018/1/26
 */
public enum ThirdOrderSourceTypeEnum {

    NONE(0, "无"),

    /**
     * 任意停车
     */
    ANYPARKING(1, "任意停车"),

    /**
     * 支付宝
     */
    ALIPAY(1, "支付宝"),

    /**
     * 海康威视
     */
    HIKVISION(2, "海康威视"),
    ;

    /**
     * 值
     */
    @Getter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    ThirdOrderSourceTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ThirdOrderSourceTypeEnum parse(Integer value) {
        if (value != null) {
            ThirdOrderSourceTypeEnum[] array = values();
            for (ThirdOrderSourceTypeEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }
}