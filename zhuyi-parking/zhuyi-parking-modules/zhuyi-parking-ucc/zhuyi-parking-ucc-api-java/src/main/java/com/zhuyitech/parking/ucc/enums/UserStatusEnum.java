package com.zhuyitech.parking.ucc.enums;


import lombok.Getter;
import lombok.Setter;

/**
 * 用户状态枚举
 *
 * @author walkman
 * @date 2018-01-10
 */
public enum UserStatusEnum {

    /**
     * 等待认证
     */
    PENDING_APPROVAL(1, "等待认证"),
    /**
     * 可用
     */
    ENABLED(2, "可用"),
    /**
     * 锁定
     */
    LOCKED(3, "锁定");
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

    UserStatusEnum(int value, String comment) {
        this.value = value;
        this.comment = comment;
    }

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
                if (value == each.value) {
                    return each;
                }
            }
        }
        return null;
    }
}
