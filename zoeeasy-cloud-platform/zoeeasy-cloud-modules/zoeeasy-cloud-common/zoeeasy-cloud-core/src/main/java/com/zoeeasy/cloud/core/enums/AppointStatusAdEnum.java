package com.zoeeasy.cloud.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 预约状态后台枚举
 *
 * @author zwq
 * @date 201-06-27
 */
@AllArgsConstructor
@NoArgsConstructor
public enum AppointStatusAdEnum {

    ALL(1, "全部预约状态"),

    USERIN(2, "预约中"),

    CANCEL(3, "已取消"),

    SUCCESS(4, "已完成");

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

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static AppointStatusAdEnum parse(Integer value) {
        if (value != null) {
            AppointStatusAdEnum[] array = values();
            for (AppointStatusAdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
