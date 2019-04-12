package com.zhuyitech.parking.tool.enums;


import lombok.Getter;

/**
 * 违章处理状态
 *
 * @author walkman
 */
public enum ViolationProcessStatus {

    /**
     * 1：未处理，2：处理中，3：已处理，4：不支持
     */
    UNTREATED(1, "未处理"),
    TREADING(2, "处理中"),
    TREATED(3, "已处理"),
    NOT_TREATED(4, "不支持");

    /**
     * 值
     */
    @Getter
    private int value;

    /**
     * 注释
     */
    @Getter
    private String comment;

    ViolationProcessStatus(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ViolationProcessStatus parse(Integer value) {
        if (value != null) {
            ViolationProcessStatus[] array = values();
            for (ViolationProcessStatus each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
