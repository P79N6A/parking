package com.zoeeasy.cloud.charge.mapper;

import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zoeeasy.cloud.charge.domain.AppointmentRuleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zwq
 * @since 2018-03-30
 */
@Repository
public interface AppointmentRuleMapper extends BaseMapper<AppointmentRuleEntity> {

    /**
     * 根据Id查询
     *
     * @param ruleId
     * @return
     */
    @SqlParser(filter = true)
    AppointmentRuleEntity selectAppointRuleById(@Param("ruleId") Long ruleId);

    /**
     * 更新预约规则
     *
     * @param appointmentRuleEntity
     * @return
     */
    @SqlParser(filter = true)
    boolean updateAppointRule(AppointmentRuleEntity appointmentRuleEntity);

}
