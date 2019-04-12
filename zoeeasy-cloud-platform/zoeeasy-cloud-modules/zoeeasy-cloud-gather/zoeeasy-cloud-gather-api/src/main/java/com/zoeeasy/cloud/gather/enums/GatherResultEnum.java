package com.zoeeasy.cloud.gather.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 推送地磁数据枚举
 *
 * @author zwq
 */
@NoArgsConstructor
@AllArgsConstructor
public enum GatherResultEnum {

    MAGNETIC_REPORT_RECORD_INSERT_ERR(10000, "地磁检测记录插入失败"),
    MAGNETIC_DETECTOR_NOT_FOUND(10001, "地磁检测器不存在"),
    TOKEN_ERR(10002, "token校验失败"),
    MAGNETIC_DETECTOR_UPDATE_ERR(10003, "地磁检测器维护失败"),
    DATATYPE_ERR(10004, "消息类型错误"),
    MAGNETIC_DETECTOR_STATUS(10005, "地磁检测器状态错误"),
    MESSAGE_PUSH_ERR(10006, "推送队列失败"),
    MAGNETIC_STATUS_RECORD_INSERT_ERR(10007, "地磁状态变更记录插入失败"),
    UPDATE_PARKINGLOTSTATUS_ERR(10008, "泊位状态更新失败"),
    MAGNETIC_HEART_BEAT_INSERT_ERR(10009, "地磁心跳记录插入失败"),;

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
    public static GatherResultEnum parse(Integer value) {
        if (value != null) {
            GatherResultEnum[] array = values();
            for (GatherResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }
}
