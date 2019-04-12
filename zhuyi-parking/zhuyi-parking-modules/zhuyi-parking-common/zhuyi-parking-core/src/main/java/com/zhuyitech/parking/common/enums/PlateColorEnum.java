package com.zhuyitech.parking.common.enums;

/**
 * 车牌颜色枚举
 *
 * @author AkeemSuper
 * @date 2018/4/20 0020
 */
public enum PlateColorEnum {

    BLUE(1, "蓝牌"),
    YELLOW(2, "黄牌"),
    WHITE(3, "白牌"),
    BLACK(4, "黑牌"),
    GREEN(5, "绿牌"),
    NEW_ENERGY(6, "新能源");

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    public Integer getValue() {
        return value;
    }

    public String getComment() {
        return this.comment;
    }

    PlateColorEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

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
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}
