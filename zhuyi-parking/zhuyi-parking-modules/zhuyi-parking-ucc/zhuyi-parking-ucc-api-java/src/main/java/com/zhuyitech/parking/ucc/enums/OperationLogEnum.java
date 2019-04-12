package com.zhuyitech.parking.ucc.enums;

/**
 * 操作日志类型枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum OperationLogEnum {

    /**
     * 接入日志
     */
    TYPE_ACCESS("1", "接入日志"),

    /**
     * 错误日志
     */
    TYPE_EXCEPTION("2", "错误日志"),;

    /**
     * 值
     */
    private String value;

    /**
     * 注释
     */
    private String comment;

    public String getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }

    OperationLogEnum(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static OperationLogEnum parse(String value) {
        if (value != null) {
            OperationLogEnum[] array = values();
            for (OperationLogEnum each : array) {
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
