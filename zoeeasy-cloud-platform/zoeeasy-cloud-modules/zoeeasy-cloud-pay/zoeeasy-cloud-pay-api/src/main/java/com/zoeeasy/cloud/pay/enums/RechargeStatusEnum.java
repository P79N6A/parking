package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 充值状态枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum RechargeStatusEnum {

    /**
     * 交易创建，等待支付
     */
    WAITING_PAY(1, "交易创建，等待支付"),

    /**
     * 充值处理中
     */
    TRADE_PROCESSING(2, "已付款，支付处理中"),

    /**
     * 未付款交易超时关闭，或支付完成后全额退款
     */
    TRADE_CLOSED(3, "未付款交易超时关闭，或支付完成后全额退款,或取消充值"),

    /**
     * 充值成功
     */
    TRADE_SUCCESS(4, "支付成功"),

    /**
     * 充值结束，不可退款
     */
    TRADE_FINISHED(5, "充值结束，不可退款"),
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

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static RechargeStatusEnum parse(Integer value) {
        if (value != null) {
            RechargeStatusEnum[] array = values();
            for (RechargeStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
