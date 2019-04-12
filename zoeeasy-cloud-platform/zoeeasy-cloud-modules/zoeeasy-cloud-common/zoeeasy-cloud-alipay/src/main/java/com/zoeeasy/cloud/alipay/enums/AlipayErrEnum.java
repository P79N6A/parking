package com.zoeeasy.cloud.alipay.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 支付宝错误枚举
 *
 * @author walkman
 * @date 2018-01-11
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AlipayErrEnum {

    PARAM_EMPTY(12000, "参数为空"),
    REQUEST_FAILED(12001, "请求失败"),
    RESPONSE_FAILED(12002, "返回失败"),
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
    public static AlipayErrEnum parse(Integer value) {
        if (value != null) {
            AlipayErrEnum[] array = values();
            for (AlipayErrEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
