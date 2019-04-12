package com.zoeeasy.cloud.integration.utils;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.enums.ParkingFreeLevelEnum;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author AkeemSuper
 * @description 计算两个时间差
 * @date 2018/3/22 0022
 */
public class CommonUtils {

    private CommonUtils() {
    }

    private static final String TIME_FORMAT = "HH:mm:ss";

    private static final String START_TIME = "00:00:00";

    /**
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 相差天数
     */
    public static int differentDays(DateTime startTime, DateTime endTime) {
        //结束时间加一天毫秒值
        long startTimeMillis = startTime.withTimeAtStartOfDay().getMillis();
        //开始时间毫秒值
        long endTimeMillis = endTime.withTimeAtStartOfDay().plusDays(1).getMillis();
        Long difference = (endTimeMillis - startTimeMillis) / (1000 * 60 * 60 * 24);
        int days = difference.intValue();
        if (START_TIME.equals(endTime.toString(TIME_FORMAT)) && days > 1) {
            days = days - 1;
        }
        return days;
    }

    /**
     * 计算车位空闲度
     *
     * @param totalAmount     总数
     * @param availableAmount 可用数
     */
    public static Integer calculateFreeLevel(Integer totalAmount, Integer availableAmount) {

        if (totalAmount == null || availableAmount == null) {
            return ParkingFreeLevelEnum.GREEN.getValue();
        }
        if (totalAmount <= 0 || availableAmount <= 0) {
            return ParkingFreeLevelEnum.RED.getValue();
        }
        //计算空闲百分比
        Double ratio = Double.valueOf(availableAmount) / Double.valueOf(totalAmount);
        if (totalAmount > PmsConstant.HUGE) {
            if (ratio >= 0.1d) {
                return ParkingFreeLevelEnum.GREEN.getValue();
            }
        } else if (totalAmount > PmsConstant.BIG && totalAmount <= PmsConstant.HUGE) {
            if (ratio >= 0.3d) {
                return ParkingFreeLevelEnum.GREEN.getValue();
            }
        } else if (totalAmount > PmsConstant.MIDDLE && totalAmount <= PmsConstant.BIG) {
            if (ratio >= 0.5d) {
                return ParkingFreeLevelEnum.GREEN.getValue();
            }
        } else {
            if (ratio >= 0.9d) {
                return ParkingFreeLevelEnum.GREEN.getValue();
            }
        }
        return ParkingFreeLevelEnum.YELLOW.getValue();
    }

    public static Date[] getIntegralHourPart(int gap) {
        Date nowDate = new Date();
        nowDate.setMinutes(0);
        nowDate.setSeconds(0);
        Date hourDate = DateTimeUtils.addHour(nowDate, gap);
        Date[] dates = new Date[2];
        dates[0] = nowDate;
        dates[1] = hourDate;
        return dates;
    }
}
