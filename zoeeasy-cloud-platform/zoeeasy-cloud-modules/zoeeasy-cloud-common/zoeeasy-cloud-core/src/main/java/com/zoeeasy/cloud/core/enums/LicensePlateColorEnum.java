package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 车牌颜色枚举
 *
 * @author walkman
 * @date 2018-01-15
 */
@NoArgsConstructor
@AllArgsConstructor
public enum LicensePlateColorEnum {

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

    @Getter
    private Integer value;

    @Getter
    private String name;

    /**
     * 解析
     *
     * @param code
     * @return
     */
    public static LicensePlateColorEnum parse(Integer code) {
        LicensePlateColorEnum[] codes = values();
        for (LicensePlateColorEnum each : codes) {
            if (each.value.equals(code)) {
                return each;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
