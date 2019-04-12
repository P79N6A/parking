package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Description 易姆讯cmd枚举
 * @Date: 2018/11/2
 * @author: lhj
 */
@NoArgsConstructor
@AllArgsConstructor
public enum InMotionCmdEnum {
    /**
     * sendParkStatu
     */
    SENDPARKSTATU(1, "sendParkStatu"),

    /**
     * sendDeviceHeartbeat
     */
    SENDDEVICEHEARTBEAT(2, "sendDeviceHeartbeat");

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
    public static InMotionCmdEnum parse(Integer value) {
        if (value != null) {
            InMotionCmdEnum[] array = values();
            for (InMotionCmdEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
