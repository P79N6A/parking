package com.zoeeasy.cloud.charge.chg;

import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import org.joda.time.DateTime;

/**
 * @Description: 计算金额策略
 * @Author: AkeemSuper
 * @Date: 2018/3/6 0006
 */
public interface CalculateAmountStrategy {

    /**
     * 停车在该规则时段内的金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     */
    Integer calculateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule);
}
