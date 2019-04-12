package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 * 短信平台异常枚举
 *
 * @author walkman
 */
public enum SmsResultCodeEnum {

    //登录相关
    LOGIN_PASSWORD_NOT_MATCH(4001, "密码错误"),
    LOGIN_DUPLICATE_USERNAME(4002, "存在多个同用户名记录"),
    LOGIN_USER_NOT_EXISTS(4003, "用户名不存在"),
    LOGIN_USER_LOCKED(4004, "用户已被锁定，无法登录"),

    //Config相关
    CONFIG_IS_EXIST(5001, "参数已存在"),
    ATTRIBUTE_IS_NULL(5002, "属性不能为空"),
    URL_CONFIG_IS_NOT_EXISTS(5003, "无对应环境配置！"),
    DB_CONFIG_IS_EXISTS(5004, "数据库配置已存在！"),

    //Process相关
    PROCESS_NAME_IS_EXIST(6001, "流程名已存在"),
    INTERFACE_DATA_IS_EXIST(6002, "接口数据组别已存在"),
    INTERFACE_DATA_ERROR(6003, "接口数据不存在"),
    PROCESS_INFO_DELETE_ERROR(6004, "流程详细删除错误"),

    //Project相关
    PROJECT_IS_EXIST(7001, "项目已存在"),

    //GlobaleVariable相关
    VARIABLE_IS_EXIST(8001, "变量已存在"),

    APPLICATION_IS_EXIST(9001, "应用已存在"),

    DATA_SOURCE_EXECUTE_ERROR(10001, "数据库执行失败"),

    PARAM_IS_EXIST(11001, "参数名已存在原始参数列表中"),

    //短信相关
    MSG_FAIL_PHONE_NULL(12001, "手机号码不能为空"),

    MSG_FAIL_LENGTH(12002, "手机号长度错误"),

    MSG_FAIL_RULE(12003, "请输入正确的手机号码"),

    MSG_FAIL_HOUR_OUTRANGE(12004, "获取验证码太频繁，请稍等片刻"),

    MSG_FAIL_OUTRANGE(12005, "获取验证码太频繁，请明天再来吧"),

    MSG_FAIL_UNKNOWN(12006, "获取验证码失败"),

    MSG_QUERY_SUCCESS(12007, "查询成功"),

    MSG_IMAGE_VALIDATE_FAIL(12008, "请输入正确的图片验证码"),

    TEMPLATE_UNDEFINED(12009, "短信模板未指定"),

    TEMPLATE_NOT_EXIST(12010, "短信模板不存在"),

    MSG_FAIL_IP_MISSING(12011, "无法获取IP地址"),

    MSG_FAIL_IP_SEND_OUT_RANGE(12012, "今日短信发送量已经超过20次限制,请明日再试哦"),

    CLIENT_NO_UNDEFINED(12013, "客户端号未指定"),

    CLIENT_NO_NOT_EXIST(12014, "客户端不存在"),

    SMS_CHANNEL_NOT_EXIST(12015, "短信通道不存在"),

    SMS_CHANNEL_GET_FAIL(12016, "短信通道获取失败"),

    SMS_SEND_FAIL(12017, "短信发送失败"),

    VERIFY_CODE_CHECK_COUNT_FAIL(12018, "验证码连续校验失败5次以上，请重新获取验证码"),

    VERIFY_CODE_CHECK_FAIL(12019, "验证码校验错误，请重新校验"),

    PARAM_IS_NULL(12020, "参数不能为空"),

    VERIFY_CODE_EXPIRED_ERROR(12021, "验证码已过期，请重新获取"),

    SMS_REQUEST_IF_CHECK_FAIL(12022, "短信回执校验错误,请重新校验");

    /**
     * 值
     */
    @Getter
    private Integer code;

    /**
     * 注释
     */
    @Getter
    private String message;

    private SmsResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static SmsResultCodeEnum getByCode(int code) {
        for (SmsResultCodeEnum type : values()) {
            if (type.getCode() == (code)) {
                return type;
            }
        }
        return null;
    }

}
