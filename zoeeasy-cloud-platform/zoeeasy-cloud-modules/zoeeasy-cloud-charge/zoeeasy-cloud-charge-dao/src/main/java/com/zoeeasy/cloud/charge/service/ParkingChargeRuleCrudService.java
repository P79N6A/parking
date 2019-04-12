package com.zoeeasy.cloud.charge.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.ParkingChargeRuleEntity;

import java.util.List;

/**
 * @author AkeemSuper
 * @date 2018/9/13 0013
 */
public interface ParkingChargeRuleCrudService extends CrudService<ParkingChargeRuleEntity> {

    /**
     * 根据规则ID获取
     *
     * @param ruleId
     * @return
     */
    List<ParkingChargeRuleEntity> findListByRuleId(Long ruleId);

    /**
     * 查找收费规则
     *
     * @param entityWrapper
     * @return
     */
    List<ParkingChargeRuleEntity> selectChargeRule(Wrapper<ParkingChargeRuleEntity> entityWrapper);

    /**
     * 根据规则ID查询是否有关联停车场
     *
     * @param ruleId
     * @return
     */
    Integer selectCountByRuleId(Long ruleId);

    /**
     * 查询停车场收费规则条数
     * @param parkingId
     * @return
     */
    Integer selectChargeTotal(Long parkingId);

    /**
     * 分页获取收费规则(无租户)
     * @param page
     * @param ew
     * @return
     */
    List<ParkingChargeRuleEntity> selectChargeRulePaged(Pagination page, Wrapper<ParkingChargeRuleEntity> ew);

    /**
     * 删除停车场关联的收费规则(无租户)
     * @param ew
     * @return
     */
    boolean deleteParkingChargeRuleNonTenant(Wrapper<ParkingChargeRuleEntity> ew);
}
