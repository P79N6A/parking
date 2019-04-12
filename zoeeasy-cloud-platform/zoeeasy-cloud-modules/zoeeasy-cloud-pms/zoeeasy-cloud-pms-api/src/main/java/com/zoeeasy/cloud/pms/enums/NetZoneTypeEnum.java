package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author AkeemSuper
 * @date 2018/12/15 0015
 */
@NoArgsConstructor
@AllArgsConstructor
public enum NetZoneTypeEnum {
    /**
     * 公网
     */
    PUBLIC_NET(1, "公网"),
    /**
     * 专网
     */
    PROTECTED_NET(2, "专网");

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
    public static NetZoneTypeEnum parse(Integer value) {
        if (value != null) {
            NetZoneTypeEnum[] array = values();
            for (NetZoneTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
