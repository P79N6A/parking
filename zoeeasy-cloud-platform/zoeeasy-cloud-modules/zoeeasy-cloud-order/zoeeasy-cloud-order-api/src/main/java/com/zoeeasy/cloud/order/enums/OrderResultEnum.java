package com.zoeeasy.cloud.order.enums;

import lombok.Getter;

/**
 * @author zwq
 * @date 2018/10/15 0015
 */
public enum OrderResultEnum {

    //预约
    PLAN_APPOINT_BEGINTIME_EMPTY(11112, "预计预约开始时间为空"),
    APPOINT_PLACE_ORDER_ERR(11113, "预约下单失败"),
    APPOINT_AMOUNT_EMPTY(11114, "预约金额为空"),
    APPOINT_ORDER_NO_EMPTY_ERROR(11116, "订单号为空"),
    APPOINT_ORDER_NOT_FOUND(11117, "预约订单不存在"),
    AMOUNT_DIFFERENT(11115, "预约金额不一致"),
    GET_USER_APPOINT_ERR(11118, "获取预约订单失败"),
    PAYING_TRY_LATER(11119, "支付处理中,请稍后再试"),
    APPOINT_RULE_NOT_FOUND(11120, "预约规则不存在"),
    PARKING_APPOINT_NOT_SUPPORT_ERROR(11121, "该停车场暂时不支持预约"),
    SCHEDULE_TIME_ERROR(1111230, "入场时间不能小于当前时间"),
    SCHEDULE_TIME_EMPTY(1111230, "入场时间不能大于当前时间"),
    GET_USER_APPOINT_LIST_ERR(11122, "获取预约订单列表失败"),
    CANCEL_APPOINT_ORDER_ERR(111223, "取消预约订单失败"),
    APPOINT_ORDER_PAYSTATUS_ERR(111224, "预约订单支付状态有误"),
    OVERTIME_CANCEL_FALSE(111225, "预约订单不允许超时取消"),
    OVER_CANCEL_LIMITTIME(111226, "超过取消时限不允许手动取消"),
    APPOINT_STATUS_ERR(111227, "本次预约已取消"),
    CLOSE_PAYTIMEOUT_ORDER_ERR(111228, "关闭超时未支付订单失败"),
    CLOSE_ENTERTIMEOUT_ORDER_ERR(111229, "关闭过期预约订单失败"),
    ORDER_ID_EMPTY(111230, "预约订单ID为空"),
    REMARK_ERR(111231, "备注失败"),
    GET_APPIONTSTATUS_LIST_ERR(111232, "获取预约状态列表失败"),
    GET_PAYWAY_LIST_ERR(111233, "获取支付方式列表失败"),
    GET_ENTRYSTATUS_LIST_ERR(111234, "获取入场状态列表失败"),
    GET_PAYSTATUS_LIST_ERR(111235, "获取支付状态列表失败"),
    USER_ID_EMPTY(111236, "用户ID为空"),
    JUDGE_ERR(111237, "判断失败"),
    USER_APPOINTORDER_EXIST(111238, "您已预约车位，不能重复预约"),
    PLATENUMBER_EMPTY(111239, "车牌号为空"),
    UPDATE_APPOINTORDER_ERR(111240, "更新预约订单失败"),
    CLOSE_ORDER_ERR(111241, "MQ关闭预约订单失败"),
    OPERATE_RECORD_ERR(111242, "MQ处理支付记录失败"),

    //停车记录
    PARKING_RECORD_NOT_FOUND(206100, "停车记录不存在"),
    PASSING_RECORD_ENTRY_NOT_FOUND(206101, "入车记录不存在"),
    PASSING_RECORD_EXIT_NOT_FOUND(206102, "出车记录不存在"),
    PASSING_RECORD_NOT_FOUND(206103, "过车记录不存在"),
    PARKING_PLATE_NUMBER_EMPTY(206104, "车牌号为空"),
    PARKING_ORDER_NO_EMPTY(206105, "订单号为空"),
    PARKING_ORDER_NOT_FOUND(206106, "停车订单不存在"),
    PARKING_ORDER_ENTRY_NOT_FOUND(206107, "入车记录不存在"),
    PARKING_ORDER_EXIT_NOT_FOUND(206108, "出车记录不存在"),
    PARKING_ORDER_PAY_STATUS_INVALID(206109, "停车订单支付状态无效"),
    PARKING_ORDER_PAYSUSSESS_CANT_UPDATE(206110, "停车订单已支付不可修改"),
    PARKING_ORDER_STATUS_GET_ERR(206111, "停车订单状态获取失败"),
    PARKING_ORDER_TYPE_GET_ERR(206112, "停车订单类型获取失败"),
    PARKING_INFO_EMPTY(206113, "停车场不存在"),
    PARKING_ORDER_AMOUNT_INVALID(206114, "订单支付金额无效"),
    PARKING_ORDER_CANNOT_PAYED(206115, "订单异常，暂无法支付"),
    PARKING_ORDER_UPDATE_ERR(206116, "更新停车订单失败"),
    PARKING_ORDER_LIST_GET_ERR(206116, "停车订单列表获取失败"),

    //海康
    SUCCESS(0, "接口调用成功，并正常返回"),
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

    OrderResultEnum(Integer value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    /**
     * 根据值获取对应的枚举
     *
     * @param value 枚举的数值
     * @return 成功返回相应的枚举，否则返回null。
     */
    public static OrderResultEnum parse(Integer value) {
        if (value != null) {
            OrderResultEnum[] array = values();
            for (OrderResultEnum each : array) {
                if (value.equals(each.value)) {
                    return each;
                }
            }
        }
        return null;
    }

}
