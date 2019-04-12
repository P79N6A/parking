package com.zoeeasy.cloud.charge.service.impl;

import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.ChargeRuleTimeEntity;
import com.zoeeasy.cloud.charge.mapper.ChargeRuleTimeMapper;
import com.zoeeasy.cloud.charge.service.ChargeRuleTimeCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("chargeRuleTimeCrudService")
public class ChargeRuleTimeCrudServiceImpl extends CrudServiceImpl<ChargeRuleTimeMapper, ChargeRuleTimeEntity> implements ChargeRuleTimeCrudService {

    @Override
    public Integer deleteByRuleId(Long ruleId) {
        EntityWrapper<ChargeRuleTimeEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return baseMapper.delete(entityWrapper);
    }

    @Override
    public List<ChargeRuleTimeEntity> selectByRuleId(Long ruleId) {
        EntityWrapper<ChargeRuleTimeEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据ruleId查找
     *
     * @param id
     * @return
     */
    @Override
    public List<ChargeRuleTimeEntity> findRuleId(Long id, Long tenantId) {
        return baseMapper.findRuleId(id, tenantId);
    }
}
