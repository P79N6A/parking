package com.zhuyitech.parking.pms.enums;

import lombok.Getter;

/**
 * 收费规则枚举
 *
 * @Date: 2018/1/26
 * @author: yuzhicheng
 */
public enum ChargeRuleTypeEnum {

    /**
     * 规则类型 1 不收费,2 按时计费 3,按次计费 4,日夜分时分次计费
     */
    GIST_TO_FREE(1, "不收费"),

    GIST_TO_DATE(2, "按时收费"),

    GIST_TO_TIMES(3, "按次收费"),

    GIST_TO_DATE_TIMES(4, "日夜分时分次计费"),
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

    ChargeRuleTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ChargeRuleTypeEnum parse(Integer value) {
        if (value != null) {
            ChargeRuleTypeEnum[] array = values();
            for (ChargeRuleTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
