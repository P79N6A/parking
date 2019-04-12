package com.zoeeasy.cloud.sys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2019/2/26.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum StatusEnum {

    NOT_AVAILABLE(0, "停用"),

    AVAILABLE(1, "正常"),;

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
    public static StatusEnum parse(Integer value) {
        if (value != null) {
            StatusEnum[] array = values();
            for (StatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
