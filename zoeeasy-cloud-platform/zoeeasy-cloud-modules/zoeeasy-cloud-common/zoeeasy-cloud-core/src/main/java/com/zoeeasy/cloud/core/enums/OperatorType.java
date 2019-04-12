package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 操作人类别
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum OperatorType {

    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 后台用户
     */
    MANAGE(1, "后台用户"),

    /**
     * 手机端用户
     */
    MOBILE(2, "手机端用户");

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
    public static OperatorType parse(Integer value) {
        if (value != null) {
            OperatorType[] array = values();
            for (OperatorType each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
