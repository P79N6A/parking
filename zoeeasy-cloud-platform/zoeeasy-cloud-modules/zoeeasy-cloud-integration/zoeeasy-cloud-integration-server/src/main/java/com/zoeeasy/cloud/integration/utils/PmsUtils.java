package com.zoeeasy.cloud.integration.utils;

import com.scapegoat.infrastructure.common.utils.DateTimeUtils;
import com.zoeeasy.cloud.pms.cst.PmsConstant;
import com.zoeeasy.cloud.pms.enums.ParkingFreeLevelEnum;

import java.util.Date;

/**
 * @author AkeemSuper
 * @date 2018/9/14 0014
 */
public final class PmsUtils {

    private PmsUtils() {
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
