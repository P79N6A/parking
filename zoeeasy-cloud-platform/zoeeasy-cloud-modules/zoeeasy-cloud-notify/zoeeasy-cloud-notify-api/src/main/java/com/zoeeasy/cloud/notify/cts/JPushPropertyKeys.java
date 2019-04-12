package com.zoeeasy.cloud.notify.cts;

/**
 * @author AkeemSuper
 * @date 2018/11/19 0019
 */
public final class JPushPropertyKeys {

    private JPushPropertyKeys() {
    }

    public static final String JPUSH_MASTER_SECRETE = "jiguang.jpush.parking.mastersecrete";
    public static final String JPUSH_APPKEY = "jiguang.jpush.parking.appkey";
    public static final String JPUSH_APNSPRODUCTION = "jiguang.jpush.parking.apnsproduction";
    public static final String JPUSH_TTL = "jiguang.jpush.parking.ttl";
    public static final String JPUSH_MESSAGE_NEW_ORDER = "parking.notification.message.template.new_order";
    public static final String JPUSH_MESSAGE_ORDER_PAYED = "parking.notification.message.template.order_payed";
    public static final String JPUSH_TEMPLATE_ORDER_PAYED = "parking.notification.push.template.order_payed";
    public static final String JPUSH_TEMPLATE_NEW_ORDER = "parking.notification.push.template.new_order";
    public static final String JPUSH_TEMPLATE_TRAFFIC_LIMIT = "parking.notification.push.template.traffic_limit";
    public static final String JPUSH_TEMPLATE_PARKING_ENTER = "parking.notification.push.template.parking_enter";
    public static final String JPUSH_TEMPLATE_PASS_RECORD_INTO_INSPECT = "cloud.notification.push.template" +
            ".pass_record_into_inspect";
    public static final String JPUSH_TEMPLATE_PASS_RECORD_OUT_INSPECT = "cloud.notification.push.template" +
            ".pass_record_out_inspect";

    public static final String MESSAGE_TEMPLATE_PASS_RECORD_INTO_INSPECT = "cloud.notification.message.template" +
            ".pass_record_into_inspect";
    public static final String MESSAGEH_TEMPLATE_PASS_RECORD_OUT_INSPECT = "cloud.notification.message.template" +
            ".pass_record_out_inspect";
}
