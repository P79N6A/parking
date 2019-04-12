package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum FixedTypeEnum {

    OWNER(1, "业主车"),
    INSIDE(2, "内部车"),

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
    public static FixedTypeEnum parse(Integer value) {
        if (value != null) {
            FixedTypeEnum[] array = values();
            for (FixedTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
