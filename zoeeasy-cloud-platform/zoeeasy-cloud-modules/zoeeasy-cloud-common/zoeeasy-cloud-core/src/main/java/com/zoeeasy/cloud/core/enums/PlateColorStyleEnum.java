package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 车牌颜色枚举
 * @Date: 2018/1/12 0012
 * @author: AkeemSuper
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PlateColorStyleEnum {

    /**
     * 其他
     */
    OTHER(0, "其他"),

    /**
     * 蓝色
     */
    BLUE(1, "蓝色"),

    /**
     * 黄色
     */
    YELLOW(2, "黄色"),

    /**
     * 黑色
     */
    BLACK(3, "黑色"),

    /**
     * 白色
     */
    WHITE(4, "白色"),

    /**
     * 绿色
     */
    GREEN(5, "绿色"),
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
    public static PlateColorStyleEnum parse(Integer value) {
        if (value != null) {
            PlateColorStyleEnum[] array = values();
            for (PlateColorStyleEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
