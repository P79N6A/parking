package com.zoeeasy.cloud.message.constant;

public class Constant {

    private Constant() {
    }

    /**
     * 地磁过车交换器名称
     */
    public static final String MAGNETIC_PASSING_EXCHANGE = "magnetic.passing.topic";

    /**
     * 地磁过车消息路由键
     */
    public static final String MAGNETIC_PASSING_ROUTING_KEY = "magnetic.passing.vehicle";

    /**
     * 处理地磁异常过车消息路由键
     */
    public static final String MAGNETIC_PASSING_ROUTING_KEY_FAIL = " magnetic.passing.failure.vehicle";

    /**
     * 地磁过车消息队列名称
     */
    public static final String MAGNETIC_PASSING_VEHICLE_RECORD_QUEUE_NAME = " magnetic.passing.vehicle.record";

    public static final String MAGNETIC_PASSING_VEHICLE_RECORD_QUEUE_NAME_FAIL = " magnetic.passing.vehicle.record.fail";

    /**
     * 海康过车交换器名称
     */
    public static final String HIK_VISION_PASSING_EXCHANGE = "hikcision.passing.topic";

    /**
     * 海康过车消息路由键
     */
    public static final String HIK_VISION_PASSING_ROUTING_KEY = "hikcision.passing.vehicle";

    /**
     * 处理海康异常过车消息路由键
     */
    public static final String HIK_VISION_PASSING_ROUTING_KEY_FAIL = "hikvision.passing.failure.vehicle";

    /**
     * 海康过车消息队列名称
     */
    public static final String HIK_VISION_PASSING_VEHICLE_RECORD_QUEUE_NAME = " hikvision.passing.vehicle.record";

    public static final String HIK_VISION_PASSING_VEHICLE_RECORD_QUEUE_NAME_FAIL = " hikvision.passing.vehicle.record.fail";
}
