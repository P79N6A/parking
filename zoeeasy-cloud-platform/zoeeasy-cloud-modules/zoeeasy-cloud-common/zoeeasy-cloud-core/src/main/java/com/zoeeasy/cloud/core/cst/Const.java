package com.zoeeasy.cloud.core.cst;

/**
 * 常量定义
 *
 * @author walkman
 */
public final class Const {

    public static final String FORMAT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DATE_MONTH = "yyyy-MM";
    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String CHARSET_CHINESE = "GBK";
    public static final String CHARSET_LATIN = "ISO-8859-1";
    public static final String TIMEZONE_GMT8 = "GMT+8";

    public static final Integer SMALL_INT_MAX_VALUE = 32767;

    /**
     * 最小计时单位30分钟
     */
    public static final int HALF_HOUR = 30;
    /**
     * 最小计时单位30分钟
     */
    public static final int HOUR = 60;
    /**
     * 特大停车场车位数量定义
     */
    public static final int HUGE = 1000;
    /**
     * 大型停车场
     */
    public static final int BIG = 300;
    /**
     * 中型停车场
     */
    public static final int MIDDLE = 50;
    /**
     * 1.5KM
     */
    public static final Double PARKING_AROUND_DISTANCE_ONE = 1.5d;
    /**
     * 3.0KM
     */
    public static final Double PARKING_AROUND_DISTANCE_THREE = 3.0d;
    /**
     * 5.0KM
     */
    public static final Double PARKING_AROUND_DISTANCE_FIVE = 5.0d;
    /**
     * 10.0KM
     */
    public static final Double PARKING_AROUND_DISTANCE_TEN = 10.0d;
    /**
     * 最大附近停车场返回数量
     */
    public static final Long MAX_PARKING_AROUND_COUNT = 100L;
    /**
     * 千米
     */
    public static final Double KILOMETER = 1000d;
    /**
     * 米
     */
    public static final String DISTANCE_UNIT_METER = "m";
    /**
     * 千米
     */
    public static final String DISTANCE_UNIT_KILOMETER = "km";

    private Const() {

    }
}
