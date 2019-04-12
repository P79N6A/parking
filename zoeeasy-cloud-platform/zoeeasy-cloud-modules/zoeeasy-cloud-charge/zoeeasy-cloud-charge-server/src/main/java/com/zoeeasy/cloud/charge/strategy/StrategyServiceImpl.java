package com.zoeeasy.cloud.charge.strategy;

import com.zoeeasy.cloud.charge.chg.CalculateAmountStrategy;
import com.zoeeasy.cloud.charge.chg.StrategyService;
import com.zoeeasy.cloud.charge.enums.ChargeRuleTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 收费规则策略
 *
 * @author AkeemSuper
 * @date 2018/8/14 0014
 */
@Service("strategyService")
public class StrategyServiceImpl implements StrategyService {

    private final Map<String, CalculateAmountStrategy> strategyMap = new ConcurrentHashMap<>();

    @Autowired
    public StrategyServiceImpl(Map<String, CalculateAmountStrategy> strategyMap) {
        this.strategyMap.clear();
        strategyMap.forEach(this.strategyMap::put);
    }

    /**
     * 根据不同规则类型匹配不同的算法
     *
     * @param ruleType 规则类型
     * @return CalculateAmountStrategy
     */
    @Override
    public CalculateAmountStrategy createCalculateStrategy(Integer ruleType) {
        if (ruleType.compareTo(ChargeRuleTypeEnum.GIST_TO_FREE.getValue()) == 0) {
            return strategyMap.get("gistToFree");
        } else if (ruleType.compareTo(ChargeRuleTypeEnum.GIST_TO_DATE.getValue()) == 0) {
            return strategyMap.get("gistToDate");
        } else if (ruleType.compareTo(ChargeRuleTypeEnum.GIST_TO_TIMES.getValue()) == 0) {
            return strategyMap.get("gistToTimes");
        } else if (ruleType.compareTo(ChargeRuleTypeEnum.GIST_TO_DATE_TIMES.getValue()) == 0) {
            return strategyMap.get("gistToDateTimes");
        }
        return null;
    }

}
