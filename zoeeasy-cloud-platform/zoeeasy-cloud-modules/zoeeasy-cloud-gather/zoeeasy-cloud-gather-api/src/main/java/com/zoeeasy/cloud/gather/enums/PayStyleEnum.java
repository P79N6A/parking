package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 支付类型枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PayStyleEnum {
    /**
     * 现金
     */
    CASH(1, "现金"),

    /**
     * 支付宝
     */
    ALIPAY(2, "支付宝"),

    /**
     * 微信
     */
    WECHAT(3, "微信"),;

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
    public static PayStyleEnum parse(Integer value) {
        if (value != null) {
            PayStyleEnum[] array = values();
            for (PayStyleEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
