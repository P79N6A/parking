package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 可用状态枚举
 * Created by song on 2018/9/11.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum EnableStatusEnum {

    /**
     * 可用
     */
    ENABLED(1, "可用"),

    /**
     * 不可用
     */
    DISABLED(2, "不可用"),
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
    public static EnableStatusEnum parse(Integer value) {
        if (value != null) {
            EnableStatusEnum[] array = values();
            for (EnableStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
