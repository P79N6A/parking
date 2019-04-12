package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * created by kane on 2018/10/15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CodeTypeEnum {
    CODE_EXIST(1001, "编号已存在");
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
     * 根据value获取对应枚举
     *
     * @param value
     * @return
     */
    public static CodeTypeEnum parse(Integer value) {
        if (value != null) {
            CodeTypeEnum[] array = values();
            for (CodeTypeEnum temp : array) {
                if (value.equals(temp.value)) {
                    return temp;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应枚举
     *
     * @param comment
     * @return
     */
    public static CodeTypeEnum getValue(String comment) {
        if (comment != null) {
            CodeTypeEnum[] array = values();
            for (CodeTypeEnum temp : array) {
                if (comment.equals(temp.comment)) {
                    return temp;
                }
            }
        }
        return null;
    }

}
