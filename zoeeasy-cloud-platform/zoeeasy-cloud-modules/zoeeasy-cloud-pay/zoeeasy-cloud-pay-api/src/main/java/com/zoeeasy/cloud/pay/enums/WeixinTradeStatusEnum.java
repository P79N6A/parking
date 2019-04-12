package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 微信支付状态枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum WeixinTradeStatusEnum {

    /**
     * 支付成功
     */
    SUCCESS(1, "SUCCESS", "支付成功"),

    /**
     * 转入退款
     */
    REFUND(2, "REFUND", "转入退款"),

    /**
     * 未支付
     */
    NOTPAY(3, "NOTPAY", "未支付"),

    /**
     * 已关闭
     */
    CLOSED(4, "CLOSED", "已关闭"),

    /**
     * 已撤销（刷卡支付）
     */
    REVOKED(5, "REVOKED", "已撤销（刷卡支付）"),

    /**
     * 交易结束，不可退款
     */
    USERPAYING(6, "USERPAYING", "用户支付中"),

    /**
     * 支付失败(其他原因，如银行返回失败)
     */
    PAYERROR(7, "支付失败", "支付失败"),
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

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static WeixinTradeStatusEnum parse(Integer value) {
        if (value != null) {
            WeixinTradeStatusEnum[] array = values();
            for (WeixinTradeStatusEnum each : array) {
                if (value.equals(each.code)) {
                    return each;
                }
            }
        }
        return null;
    }

    public static WeixinTradeStatusEnum getByCode(int code) {
        for (WeixinTradeStatusEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        return null;
    }

    public static WeixinTradeStatusEnum getByValue(String value) {
        for (WeixinTradeStatusEnum type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;
    }

}
