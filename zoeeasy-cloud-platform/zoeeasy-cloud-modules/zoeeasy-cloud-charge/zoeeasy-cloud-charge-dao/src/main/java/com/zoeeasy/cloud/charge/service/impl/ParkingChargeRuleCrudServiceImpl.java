package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.scapegoat.infrastructure.dataplus.service.impl.CrudServiceImpl;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleEntity;
import com.zoeeasy.cloud.charge.mapper.ParkingChargeRuleMapper;
import com.zoeeasy.cloud.charge.service.ParkingChargeRuleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
@Service("parkingChargeRuleCrudService")
public class ParkingChargeRuleCrudServiceImpl extends CrudServiceImpl<ParkingChargeRuleMapper,
        ParkingChargeRuleEntity> implements ParkingChargeRuleCrudService {

    /**
     * 根据规则ID获取
     */
    @Override
    public List<ParkingChargeRuleEntity> findListByRuleId(Long ruleId) {
        EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 查找收费规则
     *
     * @param entityWrapper
     * @return
     */
    @Override
    public List<ParkingChargeRuleEntity> selectChargeRule(Wrapper<ParkingChargeRuleEntity> entityWrapper) {
        return baseMapper.selectChargeRule(entityWrapper);
    }

    @Override
    public Integer selectCountByRuleId(Long ruleId) {
        EntityWrapper<ParkingChargeRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return baseMapper.selectCount(entityWrapper);
    }

    @Override
    public Integer selectChargeTotal(Long parkingId) {
        return baseMapper.selectChargeTotal(parkingId);
    }

    /**
     * 分页获取收费规则(无租户)
     *
     * @param page
     * @param ew
     * @return
     */
    @Override
    public List<ParkingChargeRuleEntity> selectChargeRulePaged(Pagination page, Wrapper<ParkingChargeRuleEntity> ew) {
        return baseMapper.selectChargeRulePaged(page, ew);
    }

    /**
     * 删除停车场关联的收费规则(无租户)
     * @param ew
     * @return
     */
    @Override
    public boolean deleteParkingChargeRuleNonTenant(Wrapper<ParkingChargeRuleEntity> ew) {
        return baseMapper.deleteParkingChargeRuleNonTenant(ew);
    }
}
