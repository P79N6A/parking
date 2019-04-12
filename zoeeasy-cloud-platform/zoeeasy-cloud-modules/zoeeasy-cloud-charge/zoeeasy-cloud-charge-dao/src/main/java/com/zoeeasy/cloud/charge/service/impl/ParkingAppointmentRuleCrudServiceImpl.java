package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.scapegoat.infrastructure.dataplus.query.wrapper.EntityWrapper;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleEntity;
import com.zoeeasy.cloud.charge.mapper.ParkingAppointmentRuleMapper;
import com.zoeeasy.cloud.charge.service.ParkingAppointmentRuleCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zwq
 */
@Service("parkingAppointmentRuleCrudService")
public class ParkingAppointmentRuleCrudServiceImpl extends ServiceImpl<ParkingAppointmentRuleMapper, ParkingAppointmentRuleEntity> implements ParkingAppointmentRuleCrudService {

    /**
     * 根据停车场ID获取
     *
     * @param parkingId
     * @return
     */
    @Override
    public List<ParkingAppointmentRuleEntity> findListByParkingId(Long parkingId) {

        EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据规则ID获取
     *
     * @param ruleId
     * @return
     */
    @Override
    public List<ParkingAppointmentRuleEntity> findListByRuleId(Long ruleId) {
        EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return baseMapper.selectList(entityWrapper);
    }

    /**
     * 根据停车场ID删除
     *
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteByParkingId(Long parkingId) {
        EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("parkingId", parkingId);
        return delete(entityWrapper);
    }

    /**
     * 根据规则ID删除
     *
     * @param ruleId
     * @return
     */
    @Override
    public boolean deleteByRuleId(Long ruleId) {
        EntityWrapper<ParkingAppointmentRuleEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("ruleId", ruleId);
        return delete(entityWrapper);
    }

    /**
     * 查询停车场预约规则
     *
     * @param wrapper
     * @return
     */
    @Override
    public ParkingAppointmentRuleEntity selectParkingAppointRule(Wrapper<ParkingAppointmentRuleEntity> wrapper) {
        return baseMapper.selectParkingAppointRule(wrapper);
    }

    /**
     * 查询停车场预约规则列表
     *
     * @param wrapper
     * @return
     */
    @Override
    public List<ParkingAppointmentRuleEntity> selectParkingAppointRuleList(Wrapper<ParkingAppointmentRuleEntity> wrapper) {
        return baseMapper.selectParkingAppointRuleList(wrapper);
    }

    /**
     * 查询规则使用条数
     * @param ruleId
     * @return
     */
    @Override
    public Integer selectParkingAppointRuleCount(Long ruleId) {
        return baseMapper.selectParkingAppointRuleCount(ruleId);
    }

    /**
     * 根据停车场id删除关联的预约规则
     * @param parkingId
     * @return
     */
    @Override
    public boolean deleteParkingAppointRuleNonTenant(Long parkingId) {
        return baseMapper.deleteParkingAppointRuleNonTenant(parkingId);
    }
}