package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 参数类型枚举
 *
 * @author zwq
 * @date 2018-01-11
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ParamLevelEnum {

    /**
     * 主菜单
     */
    MAINMENU(1, "主菜单"),
    /**
     * 子菜单
     */
    SUBMENU(2, "子菜单"),
    /**
     * 参数
     */
    PARAM(3, "参数"),
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
    public static ParamLevelEnum parse(Integer value) {
        if (value != null) {
            ParamLevelEnum[] array = values();
            for (ParamLevelEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据注释获取对应的枚举
     *
     * @param comment 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ParamLevelEnum parseByComment(String comment) {
        if (comment != null) {
            ParamLevelEnum[] array = values();
            for (ParamLevelEnum each : array) {
                if (comment.equals(each.comment)) {
                    return each;
                }
            }
        }
        return null;
    }

}

