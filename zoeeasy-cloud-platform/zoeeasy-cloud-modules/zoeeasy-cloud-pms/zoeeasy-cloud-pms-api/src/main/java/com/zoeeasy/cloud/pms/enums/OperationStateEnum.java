package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author song
 * @date 2018/9/27
 */
@NoArgsConstructor
@AllArgsConstructor
public enum OperationStateEnum {

    AVAILABLE(0, "可用"),
    DISABLED(1, "不可用"),

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
    public static OperationStateEnum parse(Integer value) {
        if (value != null) {
            OperationStateEnum[] array = values();
            for (OperationStateEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应的枚举
     *
     * @param comment
     * @return
     */
    public static OperationStateEnum getValue(String comment) {
        if (comment != null) {
            OperationStateEnum[] array = values();
            for (OperationStateEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
