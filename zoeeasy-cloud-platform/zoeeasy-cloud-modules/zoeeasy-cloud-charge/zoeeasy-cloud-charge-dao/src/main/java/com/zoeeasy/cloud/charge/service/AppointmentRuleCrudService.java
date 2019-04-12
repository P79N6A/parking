package com.zoeeasy.cloud.charge.service;

import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zoeeasy.cloud.charge.domain.AppointmentRuleEntity;

/**
 * @author zwq
 */
public interface AppointmentRuleCrudService extends CrudService<AppointmentRuleEntity> {

    /**
     * 根据Id查询
     *
     * @param ruleId
     * @return
     */
    AppointmentRuleEntity selectAppointRuleById(Long ruleId);

    /**
     * 更新预约规则
     *
     * @param appointmentRuleEntity
     * @return
     */
    boolean updateAppointRule(AppointmentRuleEntity appointmentRuleEntity);

}