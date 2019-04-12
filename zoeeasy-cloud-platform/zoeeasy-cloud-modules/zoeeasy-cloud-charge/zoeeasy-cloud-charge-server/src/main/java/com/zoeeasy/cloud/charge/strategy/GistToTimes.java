package com.zoeeasy.cloud.charge.strategy;

import com.zoeeasy.cloud.charge.chg.CalculateAmountStrategy;
import com.zoeeasy.cloud.charge.chg.dto.result.ChargeRuleInfoViewResultDto;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

/**
 * @author AkeemSuper
 * @description 计次收费策略
 * @date 2018/3/6 0006
 */
@Service("gistToTimes")
public class GistToTimes implements CalculateAmountStrategy {

    /**
     * 停车在该规则时段内的金额
     *
     * @param chargeRuleValidStartTime 停车时段内启用该规则的开始时间
     * @param chargeRuleValidEndTime   停车时段内启用该规则的结束时间
     * @param chargeRule               停车时段的某条规则
     * @return BigDecimal
     */
    @Override
    public Integer calculateAmount(DateTime chargeRuleValidStartTime, DateTime chargeRuleValidEndTime, ChargeRuleInfoViewResultDto chargeRule) {
        if (chargeRuleValidStartTime.isEqual(chargeRuleValidEndTime)) {
            return 0;
        }
        return chargeRule.getPerPrice();
    }
}
