package com.zoeeasy.cloud.inspect.enums;

/**
 * INSPECY结果枚举
 *
 * @author zwq
 */
public enum InspectResultEnum {

    //巡检
    INSPECT_INTO_RECORD_ERR(100000, "巡检入车记录操作失败"),
    PARKINGRECORD_EMPTY(100001, "过车记录不存在"),
    PARKINGRECORD_PROCESSED(100002, "停车记录已维护"),
    INSPECT_INTORECORD_INSERT_ERR(100003, "巡检入车记录插入失败"),
    PARKINGRECORD_UPDATE_ERR(100004, "停车记录更新失败"),
    PARKING_NOT_MATCH(100005, "停车场不匹配"),
    PARKINGCURRENTINFO_NOT_FOUND(100006, "停车场当前信息不存在"),
    PARKINGCURRENTINFO_UPDATE_ERR(100007, "停车当前信息记录更新失败"),
    PLATE_NUMBER_ERROR(10008, "车牌号格式不正确"),
    INSPECT_CAR_INFO_NOT_FULL(100009, "巡检车辆信息不完整"),
    INSPECT_OUT_RECORD_ERR(100010, "巡检出车记录操作失败"),
    INSPECT_ERROR_RECORD_ERR(100011, "巡检出车记录操作失败"),
    MAGNETIC_DETECTOR_EMPTY(100012, "地磁检测器获取失败"),
    PARK_INSPECT_INSPECTOR_ERR(100013, "获取巡检人员失败"),
    PARK_NOT_UPDATE(100014, "停车场不可修改"),
    EMPTY_PARKING_LOT_NOT_OPERATOR(100015, "空闲泊位不允许操作"),

    ;

    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    InspectResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static InspectResultEnum parse(Integer value) {
        if (value != null) {
            InspectResultEnum[] array = values();
            for (InspectResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

    public Integer getValue() {
        return this.value;
    }

    public String getComment() {
        return this.comment;
    }
}
