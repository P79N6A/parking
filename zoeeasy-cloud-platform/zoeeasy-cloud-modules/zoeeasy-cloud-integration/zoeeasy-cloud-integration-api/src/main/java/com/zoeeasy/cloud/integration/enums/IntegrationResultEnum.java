package com.zoeeasy.cloud.integration.enums;

/**
 * @author AkeemSuper
 * @date 2018/10/15 0015
 */
public enum IntegrationResultEnum {

    ADD_ALIPAYMESSAGELOG_ERR(100000, "支付宝异步通知插入失败"),
    PARKING_CHARGE_INFO_EMPTY(100001, "停车场收费信息为空"),
    GET_USER_INFO_BY_USER_ID_ERROR(100002, "根据用户id获取用户信息失败"),
    UPDATE_PARKING_RECORD_IS_FAILED(100003,"修改停车记录失败"),
    UPDATE_PARKING_VEHICLE_RECORD(100004,"修改在停车辆失败"),
    PARKING_LOT_NOT_EMPTY(100005,"停车场泊位不存在"),
    PASSING_VEHICLE_RECORD_NOT_EMPTY(100006,"过车记录修改失败"),
    PARKING_LOT_ADD_FAILED(100007,"泊位添加失败"),
    PARKING_RECORD_IMAGE_FAILED(100007,"图片添加失败"),

    ;
    /**
     * 值
     */
    private Integer value;

    /**
     * 注释
     */
    private String comment;

    IntegrationResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static IntegrationResultEnum parse(Integer value) {
        if (value != null) {
            IntegrationResultEnum[] array = values();
            for (IntegrationResultEnum each : array) {
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
