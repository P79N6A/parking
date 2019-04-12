package com.zoeeasy.cloud.charge.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zoeeasy.cloud.charge.domain.AppointmentRuleEntity;
import com.zoeeasy.cloud.charge.mapper.AppointmentRuleMapper;
import com.zoeeasy.cloud.charge.service.AppointmentRuleCrudService;
import org.springframework.stereotype.Service;

/**
 * @author zwq
 */
@Service("appointmentRuleCrudService")
public class AppointmentRuleCrudServiceImpl extends ServiceImpl<AppointmentRuleMapper, AppointmentRuleEntity> implements AppointmentRuleCrudService {

    /**
     * 根据Id查询
     *
     * @param ruleId
     * @return
     */
    @Override
    public AppointmentRuleEntity selectAppointRuleById(Long ruleId) {
        return baseMapper.selectAppointRuleById(ruleId);
    }

    /**
     * 更新预约规则
     *
     * @param appointmentRuleEntity
     * @return
     */
    @Override
    public boolean updateAppointRule(AppointmentRuleEntity appointmentRuleEntity) {
        return baseMapper.updateAppointRule(appointmentRuleEntity);
    }
}