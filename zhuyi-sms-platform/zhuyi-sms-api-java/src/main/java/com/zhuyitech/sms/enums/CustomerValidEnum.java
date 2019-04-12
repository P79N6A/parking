package com.zhuyitech.sms.enums;

import lombok.Getter;

/**
 *
 */
public enum CustomerValidEnum {

    APP_WRONG(600, "app密钥错误"),

    OVER_TIMES(700, "您的短信使用次数已经用完,请充值"),

    OVERDUE(800, "您的短信服务已经逾期,请续费");

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

    private CustomerValidEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static CustomerValidEnum getByCode(int code) {
        for (CustomerValidEnum type : values()) {
            if (type.getCode() == (code)) {
                return type;
            }
        }
        return null;
    }

}
