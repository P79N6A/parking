package com.zoeeasy.cloud.ucc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户注册渠道枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum UserRegisterChannelEnum {

    /**
     * 1, 系统内置
     */
    EMBED(1, "系统内置"),

    /**
     * 2, 系统创建
     */
    SYSTEM(2, "系统创建"),

    /**
     * 3, 平台创建
     */
    PLATFORM(3, "平台创建"),

    /**
     * 4, 租户创建
     */
    TENANT(4, "租户创建"),

    /**
     * 5, 官网
     */
    PORTAL(5, "官网"),
    ;

    /**
     * 值
     */
    @Getter
    @Setter
    private Integer value;

    /**
     * 注释
     */
    @Getter
    @Setter
    private String comment;

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static UserRegisterChannelEnum parse(Integer value) {
        if (value != null) {
            UserRegisterChannelEnum[] array = values();
            for (UserRegisterChannelEnum each : array) {
                if (value.equals(each.getValue())) {
                    return each;
                }
            }
        }
        return null;
    }

}