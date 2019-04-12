package com.zoeeasy.cloud.ucc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户类型枚举
 *
 * @author walkman
 */
@NoArgsConstructor
@AllArgsConstructor
public enum UserTypeEnum {

    /**
     * 1, 超级用户
     */
    ROOT_USER(1, "超级用户"),

    /**
     * 2, 系统管理员
     */
    SYSTEM_ADMIN(2, "系统管理员"),

    /**
     * 3, 系统普通用户
     */
    SYSTEM_USER(3, "系统用户"),

    /**
     * 4, 租户管理员
     */
    TENANT_ADMIN(4, "租户管理员"),

    /**
     * 4, 租户普通用户
     */
    TENANT_USER(4, "租户普通用户");

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
    public static UserTypeEnum parse(Integer value) {
        if (value != null) {
            UserTypeEnum[] array = values();
            for (UserTypeEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}