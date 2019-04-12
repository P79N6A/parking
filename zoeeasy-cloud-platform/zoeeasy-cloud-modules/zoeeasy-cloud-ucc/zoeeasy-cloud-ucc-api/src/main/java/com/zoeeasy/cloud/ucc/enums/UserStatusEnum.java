package com.zoeeasy.cloud.ucc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 用户状态枚举
 *
 * @author walkman
 * @since 2018-10-10
 */
@NoArgsConstructor
@AllArgsConstructor
public enum UserStatusEnum {

    /**
     * 1, 正常
     */
    ENABLED(1, "正常"),

    /**
     * 2, 锁定
     */
    LOCKED(2, "锁定"),

    /**
     * 3, 已注销
     */
    CANCELED(3, "已注销");

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
    public static UserStatusEnum parse(Integer value) {
        if (value != null) {
            UserStatusEnum[] array = values();
            for (UserStatusEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
