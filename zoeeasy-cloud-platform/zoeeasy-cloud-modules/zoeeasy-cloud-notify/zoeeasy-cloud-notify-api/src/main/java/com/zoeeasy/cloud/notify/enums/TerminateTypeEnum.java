package com.zoeeasy.cloud.notify.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 版本状态枚举
 *
 * @author zwq
 */
@NoArgsConstructor
@AllArgsConstructor
public enum TerminateTypeEnum {

    ANDROID(1, "android"),
    IOS(2, "ios"),
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
    public static TerminateTypeEnum parse(Integer value) {
        if (value != null) {
            TerminateTypeEnum[] array = values();
            for (TerminateTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
