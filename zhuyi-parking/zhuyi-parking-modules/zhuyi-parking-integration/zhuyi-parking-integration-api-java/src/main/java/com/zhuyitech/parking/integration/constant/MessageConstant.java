package com.zhuyitech.parking.integration.constant;

/**
 * MQ定义相关常量
 */
public class MessageConstant {

    /**
     * 过车记录交换器名称
     */
    public static final String PARKING_PASSING_EXCHANGE = "parking.passing.topic";

    public static final String PARKING_PASSING_ROUTING_KEY = "parking.passing.hikvision.*";

    public static final String PARKING_PASSING_ROUTING_KEY_FAIL = "parking.passing.hikvision.failure";

    public static final String PARKING_PASSING_QUEUE_NAME = "parking.passing";

    public static final String PARKING_PASSING_QUEUE_NAME_FAIL = "parking.passing.failure";

    /**
     * 平台停车记录交换器
     */
    public static final String PARKING_RECORD_EXCHANGE = "parking.record.topic";

    public static final String PARKING_RECORD_ROUTING_KEY = "parking.record.*";

    public static final String PARKING_RECORD_ROUTING_KEY_FAIL = "parking.record.failure";

    public static final String PARKING_RECORD_QUEUE_NAME = "parking.record";

    public static final String PARKING_RECORD_QUEUE_NAME_FAIL = "parking.record.failure";

    /**
     * 平台订单交换器
     */
    public static final String PARKING_ORDER_EXCHANGE = "parking.order.topic";

    public static final String PARKING_ORDER_ROUTING_KEY = "parking.order.*";

    public static final String PARKING_ORDER_ROUTING_KEY_FAIL = "parking.order.failure";

    public static final String PARKING_ORDER_QUEUE_NAME = "parking.order";

    public static final String PARKING_ORDER_QUEUE_NAME_FAIL = "parking.order.failure";

    /**
     * 通知推送消息
     */
    public static final String PARKING_NOTIFICATION_EXCHANGE = "parking.notification.topic";

    public static final String PARKING_NOTIFICATION_ROUTING_KEY = "parking.notification.*";

    public static final String PARKING_NOTIFICATION_ROUTING_KEY_FAIL = "parking.notification.failure";

    public static final String PARKING_NOTIFICATION_QUEUE_NAME = "parking.notification";

    public static final String PARKING_NOTIFICATION_QUEUE_NAME_FAIL = "parking.notification.failure";

}
