package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 参数类型枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ParamTypeEnum {

    /**
     * 支付宝支付
     */
    ALIPAY(1, "支付宝"),
    /**
     * 微信支付
     */
    WEIXINPAY(2, "微信"),
    /**
     * 微信公众号支付s
     */
    JSAPIPAY(3, "微信公众号支付"),
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
    public static ParamTypeEnum parse(Integer value) {
        if (value != null) {
            ParamTypeEnum[] array = values();
            for (ParamTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据注释获取对应的枚举
     *
     * @param comment 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParamTypeEnum parseByComment(String comment) {
        if (comment != null) {
            ParamTypeEnum[] array = values();
            for (ParamTypeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}

