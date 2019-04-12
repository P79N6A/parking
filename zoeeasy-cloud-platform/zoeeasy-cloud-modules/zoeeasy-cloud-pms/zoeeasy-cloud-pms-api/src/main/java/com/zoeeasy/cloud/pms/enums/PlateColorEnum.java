package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @date: 2018/10/13.
 * @author：zm
 */
@NoArgsConstructor
@AllArgsConstructor
public enum PlateColorEnum {

    /**
     * 未知
     */
    OTHER(0, "未知"),
    /**
     * 蓝色
     */
    BLUE(1, "蓝色"),
    /**
     * 黄色
     */
    YELLOW(2, "黄色"),
    /**
     * 白色
     */
    WHITE(3, "白色"),
    /**
     * 黑色
     */
    BLACK(4, "黑色"),
    /**
     * 绿色
     */
    GREEN(5, "绿色"),
    /**
     * 新能源
     */
    NEW_GREEN(6, "新能源");
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
    public static PlateColorEnum parse(Integer value) {
        if (value != null) {
            PlateColorEnum[] array = values();
            for (PlateColorEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
