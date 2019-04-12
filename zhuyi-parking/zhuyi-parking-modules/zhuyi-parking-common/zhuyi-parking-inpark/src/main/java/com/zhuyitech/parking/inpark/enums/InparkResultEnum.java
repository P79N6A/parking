package com.zhuyitech.parking.inpark.enums;


import lombok.Getter;

/**
 * 支付宝错误枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
public enum InparkResultEnum {

    /**
     *
     */
    SUCCESS("00000", "成功"),

    FAILED("10000", "失败"),
    ;

    /**
     * 值
     */
    @Getter
    private String value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    InparkResultEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static InparkResultEnum parse(String value) {
        if (value != null) {
            InparkResultEnum[] array = values();
            for (InparkResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
