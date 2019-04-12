package com.zoeeasy.cloud.ucc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 租户状态枚举
 *
 * @author walkman
 * @since 2018-10-20
 */
@NoArgsConstructor
@AllArgsConstructor
public enum TenantStatusEnum {

    /**
     * 1, 正常
     */
    ENABLED(1, "正常"),

    /**
     * 2, 禁用
     */
    LOCKED(2, "禁用"),

    /**
     * 3, 已注销
     */
    CANCELED(3, "已注销");

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
    public static TenantStatusEnum parse(Integer value) {
        if (value != null) {
            TenantStatusEnum[] array = values();
            for (TenantStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
