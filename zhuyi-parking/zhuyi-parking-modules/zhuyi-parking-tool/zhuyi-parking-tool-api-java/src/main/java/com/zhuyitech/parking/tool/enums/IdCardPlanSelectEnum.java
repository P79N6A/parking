package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 加油站状态枚举
 *
 * @author AkeemSuper
 */
public enum IdCardPlanSelectEnum {

    /**
     * 加油站
     */
    JISHUAPI_IDCARD_VERIFY_URL(1, "极速身份证二要素校验"),
    /**
     * 加气站
     */
    ALIYUN_IDCARD_CERT_URL(2, "阿里云身份证二要素校验一"),
    /**
     * 加气站
     */
    ALIYUN_IDCARD_URL(3, "阿里云身份证二要素校验二");

    /**
     * 值
     */
    @Getter
    private int value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    IdCardPlanSelectEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static IdCardPlanSelectEnum parse(Integer value) {
        if (value != null) {
            IdCardPlanSelectEnum[] array = values();
            for (IdCardPlanSelectEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }

}
