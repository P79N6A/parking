package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/27.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayTypeEnum {

    CASH_PAYMENT(0, "现金支付"),
    ALIPAY_ONLINE(1, "支付宝在线缴费"),
    ALIPAY_WITHHOLD(2, "支付宝代扣缴费"),
    ALIPAY_FACE(3, "支付宝当面付"),
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
    public static PayTypeEnum parse(Integer value) {
        if (value != null) {
            PayTypeEnum[] array = values();
            for (PayTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应的枚举
     *
     * @param comment
     * @return
     */
    public static PayTypeEnum getValue(String comment) {
        if (comment != null) {
            PayTypeEnum[] array = values();
            for (PayTypeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
