package com.zoeeasy.cloud.inspect.enums;

/**
 * @author AkeemSuper
 * @date 2018/10/29 0029
 */
public enum InspectReportTypeEnum {

    VEHICLE_INTO(1, "车辆到达"),
    VEHICLE_OUT(2, "车辆离开"),

    ;
    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    InspectReportTypeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static InspectReportTypeEnum parse(Integer value) {
        if (value != null) {
            InspectReportTypeEnum[] array = values();
            for (InspectReportTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
