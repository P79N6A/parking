package com.zoeeasy.cloud.inspect.enums;

/**
 * 巡检异常原因枚举
 *
 * @author zwq
 */
public enum InspectReasonEnum {
    MESSAGE_REPETITION_ENTERING(1, "信息重复录入"),
    MESSAGE_ERROR_ENTERING(2, "信息错误录入"),
    ;

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    InspectReasonEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static InspectReasonEnum parse(Integer value) {
        if (value != null) {
            InspectReasonEnum[] array = values();
            for (InspectReasonEnum each : array) {
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
