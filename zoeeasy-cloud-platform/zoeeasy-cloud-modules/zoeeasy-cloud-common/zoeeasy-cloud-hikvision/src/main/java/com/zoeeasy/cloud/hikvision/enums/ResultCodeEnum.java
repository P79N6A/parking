package com.zoeeasy.cloud.hikvision.enums;

import lombok.Getter;

/**
 * 错误码说明
 *
 * @author walkman
 */
public enum ResultCodeEnum {

    SUCCESS(0, "接口调用成功，并正常返回"),
    SYSTEM_ERROR(1000, "系统异常"),
    PARAMETER_ERROR(1001, "API参数无效"),
    APP_KEY_ERROR(1002, "appkey无效"),
    TIMESTAMP_ERROR(1003, "time时间戳无效"),
    TOKEN_ERROR(1004, "token无效"),
    SERVICE_ERROR(1005, "平台服务异常"),
    REQUEST_ERROR(1006, "请求非法(请求重放)"),
    VERSION_ERROR(1007, "版本不兼容"),
    TOKEN_NULL_ERROR(1008, "token为空"),
    PARK_NOT_FOUND_ERROR(1009, "停车场不存在"),
    URL_NOT_FOUND_ERROR(1010, "请求url不存在");

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

    ResultCodeEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static ResultCodeEnum parse(Integer value) {
        if (value != null) {
            ResultCodeEnum[] array = values();
            for (ResultCodeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
