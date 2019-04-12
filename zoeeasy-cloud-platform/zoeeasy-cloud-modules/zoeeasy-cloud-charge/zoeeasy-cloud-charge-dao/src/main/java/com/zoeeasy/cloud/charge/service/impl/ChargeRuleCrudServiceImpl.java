package com.zoeeasy.cloud.charge.service.impl;

import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.ChargeRuleEntity;
import com.zoeeasy.cloud.charge.mapper.ChargeRuleMapper;
import com.zoeeasy.cloud.charge.service.ChargeRuleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("chargeRuleCrudService")
public class ChargeRuleCrudServiceImpl extends CrudServiceImpl<ChargeRuleMapper, ChargeRuleEntity> implements ChargeRuleCrudService {

    /**
     * 根据编号获取
     */
    @Override
    public ChargeRuleEntity selectByCode(String ruleCode) {
        ChargeRuleEntity chargeRule = new ChargeRuleEntity();
        chargeRule.setCode(ruleCode);
        return baseMapper.selectOne(chargeRule);
    }

    /**
     * 根据id批量查找
     *
     * @param ids ids
     * @return List
     */
    @Override
    public List<ChargeRuleEntity> findByIds(List<Long> ids) {
        return baseMapper.findByIds(ids);
    }

    /**
     * 根据id获取
     *
     * @param ruleId
     * @return
     */
    @Override
    public ChargeRuleEntity selectByRuleId(Long ruleId, Long tenantId) {
        return baseMapper.selectByRuleId(ruleId, tenantId);
    }

    @Override
    public ChargeRuleEntity selectByIdNonTenant(Long ruleId) {
        return baseMapper.selectById(ruleId);
    }
}
