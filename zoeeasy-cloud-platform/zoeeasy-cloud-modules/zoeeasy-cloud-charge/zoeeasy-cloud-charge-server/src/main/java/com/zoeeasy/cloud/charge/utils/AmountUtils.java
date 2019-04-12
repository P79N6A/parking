package com.zoeeasy.cloud.charge.utils;

import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import org.joda.time.DateTime;

/**
 * @author AkeemSuper
 * @description 计算金额工具类
 * @date 2018/3/27 0027
 */
public final class AmountUtils {

    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String START_TIME = "00:00:00";

    private AmountUtils() {
    }

    /**
     * 当天金额和日最大金额比
     *
     * @param chargeRule       规则
     * @param oneDayTotalPrice 某天计算的金额
     * @return BigDecimal
     */
    public static Integer compareDayTopAmount(ChargeRuleInfoViewResultDto chargeRule, Integer oneDayTotalPrice) {

        Integer dayTopAmount = chargeRule.getDayTopAmount();
        //按每日封顶金额计算费用
        return dayTopAmount < oneDayTotalPrice ? dayTopAmount : oneDayTotalPrice;
    }

    /**
     * 总金额和规则最大金额比
     *
     * @param chargeRule  规则
     * @param totalAmount 该规则下的总金额
     * @return BigDecimal
     */
    public static Integer compareTopAmount(ChargeRuleInfoViewResultDto chargeRule, Integer totalAmount) {
        Integer topAmount = chargeRule.getTopAmount();
        if (chargeRule.getTopStatus()) {
            return topAmount < totalAmount ? topAmount : totalAmount;
        } else {
            return totalAmount;
        }
    }

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

}
