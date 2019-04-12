package com.zoeeasy.cloud.ucc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 租户类型枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum TenantTypeEnum {

    /**
     * 1, 个人
     */
    SINGLE(1, "个人"),
    /**
     * 2, 个体
     */
    INDEPENDENT(2, "个体"),
    /**
     * 3, 企业
     */
    COMPANY(3, "企业");

    /**
     * 值
     */
    @Getter
    @Setter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    @Setter
    private String comment;

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static TenantTypeEnum parse(Integer value) {
        if (value != null) {
            TenantTypeEnum[] array = values();
            for (TenantTypeEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}
