package com.zhuyitech.parking.pms.utils;

import com.zhuyitech.parking.pms.dto.result.chargerule.ChargeRuleResultDto;

import java.math.BigDecimal;

/**
 * @author AkeemSuper
 * @description 计算金额工具类
 * @date 2018/3/27 0027
 */
public class AmountUtils {

    /**
     * 当天金额和日最大金额比
     *
     * @param chargeRule       规则
     * @param oneDayTotalPrice 某天计算的金额
     * @return BigDecimal
     */
    public static BigDecimal compareDayTopAmount(ChargeRuleResultDto chargeRule, BigDecimal oneDayTotalPrice) {

        BigDecimal dayTopAmount = chargeRule.getDayTopAmount();
        //按每日封顶金额计算费用
        BigDecimal realTotal = dayTopAmount.compareTo(oneDayTotalPrice) < 1 ? dayTopAmount : oneDayTotalPrice;
        return realTotal;
    }

    /**
     * 总金额和规则最大金额比
     *
     * @param chargeRule  规则
     * @param totalAmount 该规则下的总金额
     * @return BigDecimal
     */
    public static BigDecimal compareTopAmount(ChargeRuleResultDto chargeRule, BigDecimal totalAmount) {
        BigDecimal topAmount = chargeRule.getTopAmount();
        BigDecimal realTotal;
        if (chargeRule.getTopStatus()) {
            realTotal = topAmount.compareTo(totalAmount) < 0 ? topAmount : totalAmount;
        } else {
            realTotal = totalAmount;
        }
        return realTotal;
    }

}
