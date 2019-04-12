package com.zoeeasy.cloud.pms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by song on 2018/9/27.
 */
@NoArgsConstructor
@AllArgsConstructor
public enum AppointmentEnum {

    YES(1, "是"),
    NO(0, "否"),

    ;
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
    public static AppointmentEnum parse(Integer value) {
        if (value != null) {
            AppointmentEnum[] array = values();
            for (AppointmentEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    /**
     * 根据comment获取对应的枚举
     *
     * @param comment
     * @return
     */
    public static Boolean getValue(String comment) {
        if (comment != null) {
            if (comment.equals(AppointmentEnum.YES.comment)) {
                return true;
            }
            if (comment.equals(AppointmentEnum.NO.comment)) {
                return false;
            }
        }
        return false;
    }

}
