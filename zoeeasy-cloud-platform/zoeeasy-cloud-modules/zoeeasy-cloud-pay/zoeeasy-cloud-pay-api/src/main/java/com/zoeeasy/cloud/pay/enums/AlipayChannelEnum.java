package com.zoeeasy.cloud.pay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付宝支付渠道
 *
 * @author walkman
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum AlipayChannelEnum {

    /**
     * 余额
     */
    BALANCE(1, "余额"),

    /**
     * 余额宝
     */
    MONEYFUND(2, "余额宝"),

    /**
     * 红包
     */
    COUPON(3, "红包"),

    /**
     * 花呗
     */
    PCREDIT(4, "花呗"),

    /**
     * 花呗分期
     */
    PCREDITPAYINSTALLMENT(5, "花呗分期"),

    /**
     * 信用卡
     */
    CREDITCARD(6, "信用卡"),

    /**
     * 信用卡快捷
     */
    CREDITCARDEXPRESS(7, "信用卡快捷"),

    /**
     * 信用卡卡通
     */
    CREDITCARDCARTOON(8, "信用卡卡通"),

    /**
     * 信用支付类型（包含信用卡卡通、信用卡快捷、花呗、花呗分期）
     */
    CREDIT_GROUP(9, ""),
    /**
     *
     */
    DEBITCARDEXPRESS(10, "借记卡快捷"),

    /**
     * 商户预存卡
     */
    MCARD(11, "商户预存卡"),

    /**
     * 个人预存卡
     */
    PCARD(12, "个人预存卡"),

    /**
     * 优惠（包含实时优惠+商户优惠）
     */
    PROMOTION(13, "优惠（包含实时优惠+商户优惠）"),

    /**
     * 营销券
     */
    VOUCHER(14, "营销券"),
    /**
     * 积分
     */
    POINT(15, "积分"),

    /**
     * 商户优惠
     */
    MDISCOUNT(16, "商户优惠"),

    /**
     * 网银
     */
    BANK_PAY(17, "网银"),
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
    public static AlipayChannelEnum parse(Integer value) {
        if (value != null) {
            AlipayChannelEnum[] array = values();
            for (AlipayChannelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
