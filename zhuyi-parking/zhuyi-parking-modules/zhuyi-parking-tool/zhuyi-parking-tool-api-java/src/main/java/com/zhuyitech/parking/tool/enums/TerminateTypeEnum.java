package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 版本状态枚举
 *
 * @author zwq
 */
public enum TerminateTypeEnum {

    ANDROID(1, "android"),
    IOS(2, "ios"),
    ;

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

    TerminateTypeEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

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
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
