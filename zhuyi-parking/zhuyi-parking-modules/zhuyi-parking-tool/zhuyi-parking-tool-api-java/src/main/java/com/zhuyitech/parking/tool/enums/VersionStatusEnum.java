package com.zhuyitech.parking.tool.enums;

import lombok.Getter;

/**
 * 版本状态枚举
 *
 * @author zwq
 */
public enum VersionStatusEnum {

    UNPUBLISH(0, "未发布"),
    PUBLISH(1, "已发布"),
    UNSHELVE(2, "已下架");

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

    VersionStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static VersionStatusEnum parse(Integer value) {
        if (value != null) {
            VersionStatusEnum[] array = values();
            for (VersionStatusEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
