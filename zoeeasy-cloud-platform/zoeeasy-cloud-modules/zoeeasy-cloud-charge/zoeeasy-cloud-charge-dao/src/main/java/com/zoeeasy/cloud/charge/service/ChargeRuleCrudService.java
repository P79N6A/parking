package com.zoeeasy.cloud.charge.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.ChargeRuleEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
public interface ChargeRuleCrudService extends CrudService<ChargeRuleEntity> {

    /**
     * 根据编号获取
     */
    ChargeRuleEntity selectByCode(String ruleCode);

    /**
     * 根据id批量查找
     *
     * @param ids ids
     * @return List
     */
    List<ChargeRuleEntity> findByIds(List<Long> ids);

    /**
     * 根据id获取
     *
     * @param ruleId
     * @return
     */
    ChargeRuleEntity selectByRuleId(Long ruleId, Long tenantId);

    /**
     * 根据id获取
     *
     * @param ruleId
     * @return
     */
    ChargeRuleEntity selectByIdNonTenant(Long ruleId);
}
