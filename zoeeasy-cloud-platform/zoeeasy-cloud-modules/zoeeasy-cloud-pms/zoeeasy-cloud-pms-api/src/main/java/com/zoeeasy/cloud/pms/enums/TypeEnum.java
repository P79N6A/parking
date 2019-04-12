package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/26.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum TypeEnum {

    GROUND(1, "地面"),
    UNDERGROUND(2, "地下"),
    ROADSIDE(3, "路边"),

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
    public static TypeEnum parse(Integer value) {
        if (value != null) {
            TypeEnum[] array = values();
            for (TypeEnum each : array) {
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
    public static TypeEnum getValue(String comment) {
        if (comment != null) {
            TypeEnum[] array = values();
            for (TypeEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}
