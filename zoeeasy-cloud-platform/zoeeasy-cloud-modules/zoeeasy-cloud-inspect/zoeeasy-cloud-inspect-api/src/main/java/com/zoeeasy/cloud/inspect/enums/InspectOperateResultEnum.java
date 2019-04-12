package com.zoeeasy.cloud.inspect.enums;

/**
 * 巡查结果枚举
 *
 * @author zwq
 */
public enum InspectOperateResultEnum {

    PROCESSED(1, "已处理"),

    UNDISPOSED(2, "未处理"),
    ;

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    InspectOperateResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static InspectOperateResultEnum parse(Integer value) {
        if (value != null) {
            InspectOperateResultEnum[] array = values();
            for (InspectOperateResultEnum each : array) {
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
