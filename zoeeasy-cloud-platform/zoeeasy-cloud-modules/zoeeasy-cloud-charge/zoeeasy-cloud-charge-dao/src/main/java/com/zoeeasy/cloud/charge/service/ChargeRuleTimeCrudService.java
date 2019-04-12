package com.zoeeasy.cloud.charge.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.ChargeRuleTimeEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
public interface ChargeRuleTimeCrudService extends CrudService<ChargeRuleTimeEntity> {

    /**
     * @param ruleId
     * @return
     */
    Integer deleteByRuleId(Long ruleId);

    /**
     * 根据ruleId获取收费规则
     *
     * @return
     */
    List<ChargeRuleTimeEntity> selectByRuleId(Long ruleId);

    /**
     * 根据ruleId查找
     *
     * @param id
     * @return
     */
    List<ChargeRuleTimeEntity> findRuleId(Long id, Long tenantId);
}
