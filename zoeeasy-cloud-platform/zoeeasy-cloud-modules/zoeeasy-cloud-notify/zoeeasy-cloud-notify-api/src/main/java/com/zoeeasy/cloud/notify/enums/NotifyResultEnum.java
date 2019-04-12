package com.zoeeasy.cloud.notify.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 设备状态枚举
 *
 * @author walkman
 */
@AllArgsConstructor
@NoArgsConstructor
public enum NotifyResultEnum {

    TEMPLATE_UNDEFINED(100808, "消息模板未指定"),
    TEMPLATE_NOT_EXIST(100809, "消息模板不存在"),
    PUSH_DEVICE_REGISTRATION_ID_EMPTY(101000, "registrationId不能为空"),
    PUSH_DEVICE_REGISTER_FAILED(101001, "设备注册失败"),
    PUSH_DEVICE_BIND_FAILED(101002, "设备绑定失败"),
    PARKING_ID_NOT_EMPTY(102004, "停车场id不能为空");

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
    public static NotifyResultEnum parse(Integer value) {
        if (value != null) {
            NotifyResultEnum[] array = values();
            for (NotifyResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
