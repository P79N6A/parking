package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 过车方向枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PassingDirectionEnum {

    /**
     * 入场
     */
    ENTRANCE(0, "入场"),

    /**
     * 出场
     */
    EXIT(1, "出场"),
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
    public static PassingDirectionEnum parse(Integer value) {
        if (value != null) {
            PassingDirectionEnum[] array = values();
            for (PassingDirectionEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
