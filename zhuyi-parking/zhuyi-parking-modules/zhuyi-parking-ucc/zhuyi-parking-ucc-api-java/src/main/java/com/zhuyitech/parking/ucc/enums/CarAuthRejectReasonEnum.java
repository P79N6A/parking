package com.zhuyitech.parking.ucc.enums;

/**
 * 用户车牌颜色枚举
 *
 * @author AkeemSuper
 * @date 2018/4/20 0020
 */
public enum CarAuthRejectReasonEnum {

    PLATE_NUMBER_NOT_COINCIDE("1", "车牌号和行驶证不一致"),
    VEHICLE_NUMBER_NOT_COINCIDE("2", "车架号和行驶证不一致"),
    ENERGY_NUMBER_NOT_COINCIDE("3", "发动机和行驶证不一致"),
    REGISTER_DATE_NUMBER_NOT_COINCIDE("4", "注册日期和行驶证不一致"),
    OTHER("5", "其他");

    /**
     * 值
     */
    private String value;

    /**
     * 注释
     */
    private String comment;

    public String getValue() {
        return value;
    }

    public String getComment() {
        return this.comment;
    }

    CarAuthRejectReasonEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static CarAuthRejectReasonEnum parse(String value) {
        if (value != null) {
            CarAuthRejectReasonEnum[] array = values();
            for (CarAuthRejectReasonEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
