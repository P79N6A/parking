package com.zoeeasy.cloud.charge.chg;

/**
 * 收费策略服务
 *
 * @author AkeemSuper
 * @date 2018/10/10 0010
 */
public interface StrategyService {

    /**
     * 根据不同规则类型匹配不同的算法
     *
     * @param ruleType
     * @return
     */
    CalculateAmountStrategy createCalculateStrategy(Integer ruleType);
}
