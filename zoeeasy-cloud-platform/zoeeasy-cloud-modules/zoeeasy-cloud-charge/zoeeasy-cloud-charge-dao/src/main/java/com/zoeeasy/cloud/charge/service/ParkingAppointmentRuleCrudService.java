package com.zoeeasy.cloud.charge.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.ParkingAppointmentRuleEntity;

import java.util.List;

/**
 * @author zwq
 */
public interface ParkingAppointmentRuleCrudService extends CrudService<ParkingAppointmentRuleEntity> {

    /**
     * 根据停车场ID获取
     *
     * @param parkingId
     * @return
     */
    List<ParkingAppointmentRuleEntity> findListByParkingId(Long parkingId);

    /**
     * 根据规则ID获取
     *
     * @param ruleId
     * @return
     */
    List<ParkingAppointmentRuleEntity> findListByRuleId(Long ruleId);

    /**
     * 根据停车场ID删除
     *
     * @param parkingId
     * @return
     */
    boolean deleteByParkingId(Long parkingId);

    /**
     * 根据规则ID删除
     *
     * @param ruleId
     * @return
     */
    boolean deleteByRuleId(Long ruleId);

    /**
     * 查询停车场预约规则
     *
     * @param wrapper
     * @return
     */
    ParkingAppointmentRuleEntity selectParkingAppointRule(Wrapper<ParkingAppointmentRuleEntity> wrapper);

    /**
     * 查询停车场预约规则列表
     *
     * @param wrapper
     * @return
     */
    List<ParkingAppointmentRuleEntity> selectParkingAppointRuleList(Wrapper<ParkingAppointmentRuleEntity> wrapper);

    /**
     * 查询规则使用条数
     * @param ruleId
     * @return
     */
    Integer selectParkingAppointRuleCount(Long ruleId);

    /**
     * 根据停车场id删除关联的预约规则
     * @param parkingId
     * @return
     */
    boolean deleteParkingAppointRuleNonTenant(Long parkingId);
}