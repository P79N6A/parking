package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 阿里云短信接口调用错误码
 *
 * @@author walkman
 */
public enum AliErrorCodeEnum {

    OK(100100, "请求成功"),
    RAM_PERMISSION_DENY(100101, "RAM权限DENY"),

    OUT_OF_SERVICE(100102, "业务停机"),

    PRODUCT_UN_SUBSCRIPT(100103, "未开通云通信产品的阿里云客户"),

    PRODUCT_UNSUBSCRIBE(100104, "产品未开通"),
    ACCOUNT_NOT_EXISTS(100105, "账户不存在"),
    ACCOUNT_ABNORMAL(100106, "账户异常"),
    SMS_TEMPLATE_ILLEGAL(100107, "短信模板不合法"),
    SMS_SIGNATURE_ILLEGAL(100108, "短信签名不合法"),
    INVALID_PARAMETERS(100109, "参数异常"),
    SYSTEM_ERROR(100110, "系统错误"),
    MOBILE_NUMBER_ILLEGAL(100111, "非法手机号"),
    MOBILE_COUNT_OVER_LIMIT(100112, "手机号码数量超过限制"),
    TEMPLATE_MISSING_PARAMETERS(100113, "模板缺少变量"),
    BUSINESS_LIMIT_CONTROL(100114, "业务限流"),
    INVALID_JSON_PARAM(100115, "JSON参数不合法，只接受字符串值"),
    BLACK_KEY_CONTROL_LIMIT(100116, "黑名单管控"),
    PARAM_LENGTH_LIMIT(100117, "参数超出长度限制"),
    PARAM_NOT_SUPPORT_URL(100118, "不支持URL"),
    AMOUNT_NOT_ENOUGH(100119, "账户余额不足"),
    ;

    /**
     * 值
     */
    @Getter
    private int code;
    /**
     * 注释
     */
    @Getter
    private String message;

    private AliErrorCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static AliErrorCodeEnum getByCode(int code) {
        for (AliErrorCodeEnum type : values()) {
            if (type.getCode() == (code)) {
                return type;
            }
        }
        return null;
    }

}
