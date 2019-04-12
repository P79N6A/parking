package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/10/15.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum CarColorEnum {

    OTHER(0, "其他"),

    WHITE(1, "白色"),

    BLACK(2, "黑色"),

    RED(3, "红色"),

    BLUE(4, "蓝色"),

    SILVER(5, "银色"),

    GRAY(6, "灰色"),

    YELLOW(7, "黄色"),

    BROWN(8, "棕色"),

    PINK(9, "粉色"),

    VIOLET(10, "紫色"),

    DARK_BLUE(11, "深蓝"),

    GREEN(12, "绿色"),

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
    public static CarColorEnum parse(Integer value) {
        if (value != null) {
            CarColorEnum[] array = values();
            for (CarColorEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
