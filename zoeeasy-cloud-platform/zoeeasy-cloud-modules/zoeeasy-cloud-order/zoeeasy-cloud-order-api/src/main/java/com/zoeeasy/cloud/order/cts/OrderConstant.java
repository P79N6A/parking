package com.zoeeasy.cloud.order.cts;

/**
 * @author AkeemSuper
 * @date 2018/10/30 0030
 */
public final class OrderConstant {
    private OrderConstant() {
    }

    public static final int MIN = 2;
    public static final int MAX = 64;

    public static final String LENGTH_RANGE = "{min}-{max}个字符";

    public static final String TENANT_ID_NOT_EMPTY = "租户id不能为空";
    public static final String CHARGE_INFO_ID_NOT_EMPTY = "收费信息id不能为空";
    public static final String RECORD_NO_NOT_EMPTY = "停车记录编号不能为空";
    public static final String ORDER_NO_NOT_EMPTY = "订单号不能为空";
    public static final String ORDER_ID_NOT_EMPTY = "订单ID不能为空";
    public static final String ORDER_PAY_USER_NOT_NULL = "支付用户不能为空";
    public static final String ORDER_PAY_TIME_NOT_NULL = "支付时间不能为空";
    public static final String ORDER_PAY_AMOUNT_NOT_NULL = "支付金额不能为空";
    public static final String ORDER_PAY_AMOUNT_INVALID = "支付金额无效";
    public static final String PARKING_ID_NOT_EMPTY = "停车场id不能为空";
    public static final String PARKING_NAME_NOT_EMPTY = "停车场名称不能为空";
    public static final String PARKING_START_TIME_NOT_EMPTY = "停车开始时间不能为空";
    public static final String PLATE_NUMBER_NOT_EMPTY = "车牌号不能为空";
    public static final String PLATE_COLOR_NOT_EMPTY = "车牌颜色不能为空";
    public static final String PAY_ORDER_NO_NOT_EMPTY = "支付订单号不能为空";

    public static final String PARKING_ORDER_NO_NOT_FOUND = "订单不存在";
    public static final String PARKING_ORDER_PAY_STATUS_INVALID = "订单支付状态无效";
    public static final String PARKING_ORDER_PAYED = "订单已经支付，请勿重复支付";

    public static final String PAYWAY_NOT_EMPTY = "支付方式不能为空";
    public static final String PAYMENTAMOUNT_NOT_EMPTY = "付款金额不能为空";
    public static final String PAYWAY_NOT_CASH = "该支付方式为非线下收款";
    public static final String PAYSTATUS_NOT_EMPTY = "支付状态不能为空";
}
