package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 微信退款状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum WeixinRefundStatusEnum {

    /**
     * 退款成功
     */
    SUCCESS(1, "SUCCESS", "退款成功"),
    /**
     * 退款关闭
     */
    REFUNDCLOSE(2, "REFUNDCLOSE", "退款关闭"),
    /**
     * 退款处理中
     */
    PROCESSING(3, "PROCESSING", "退款处理中"),
    /**
     * 退款异常
     */
    CHANGE(4, "CHANGE", "退款异常"),
    ;

    /**
     * 代码
     */
    @Getter
    private Integer code;

    /**
     * 值
     */
    @Getter
    private String value;

    /**
     * 注释
     */
    @Getter
    private String message;

    public static WeixinRefundStatusEnum getByValue(String value) {
        for (WeixinRefundStatusEnum type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

    public static WeixinRefundStatusEnum getByCode(Integer code) {
        for (WeixinRefundStatusEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

}
