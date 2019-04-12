package com.zhuyitech.parking.common.utils;

import com.scapegoat.infrastructure.common.utils.DateUtils;

/**
 * 序列号工具类
 *
 * @author walkman
 * @date 2018-05-30
 */
public final class SequenceUtils {

    private SequenceUtils() {
    }

    private static final int DEFAULT_COUNT = 12;

    /**
     * 充值
     */
    private static final String RECHARGE_BUSINESS_CODE = "10001";

    /**
     * 停车
     */
    private static final String PARKING_RECORD_BUSINESS_CODE = "10002";

    /**
     * 停车
     */
    private static final String PARKING_ORDER_BUSINESS_CODE = "10003";

    /**
     * 预约订单
     */
    private static final String APPOINTMENT_ORDER_BUSINESS_CODE = "10004";

    /**
     * 支付订单
     */
    private static final String PAYMENT_ORDER_BUSINESS_CODE = "10005";

    /**
     * 过车流水号
     */
    private static final String PASSING_RECORD_BUSINESS_CODE = "10006";

    /**
     * 过车流水号
     */
    private static final String PARKING_SETTLE_BUSINESS_CODE = "10007";

    /**
     * @param businessCode businessCode
     * @param num          num
     * @return 订单号
     */
    private static String generateOrderNo(String businessCode, int num) {
        String timeNo = DateUtils.formatNowDate(DateUtils.YYYYMMDDHHMMSS);
        return businessCode + timeNo + RandomUtils.generateNumber(num);
    }

    /**
     * 生成充值订单号
     *
     * @return 充值订单号
     */
    public static String generateRechargeOrderNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(RECHARGE_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成支付订单号
     *
     * @return 支付订单号
     */
    public static String generatePaymentOrderNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(PAYMENT_ORDER_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成停车账单号
     *
     * @return 停车账单号
     */
    public static String generateParkingOrderNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(PARKING_ORDER_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成预约订单号
     *
     * @return 预约订单号
     */
    public static String generateAppointOrderNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(APPOINTMENT_ORDER_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成过车记录号
     *
     * @return 过车记录号
     */
    public static String generatePassingRecordNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(PASSING_RECORD_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成停车账单号
     *
     * @return 停车账单号
     */
    public static String generateParkingRecordNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(PARKING_RECORD_BUSINESS_CODE, DEFAULT_COUNT);
    }

    /**
     * 生成停车账单结算订单号
     *
     * @return 车账单结算订单号
     */
    public static String generateParkingSettleOrderNo() {
        int machineId = 1;
        // 最大支持到9
        return machineId + generateOrderNo(PARKING_SETTLE_BUSINESS_CODE, DEFAULT_COUNT);
    }
}
