package com.zoeeasy.cloud.integration.service.impl;

import com.baidu.unbiz.fluentvalidator.annotation.FluentValid;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.charge.appointrule.ParkingAppointRuleService;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleUpdateRequestDto;
import com.zoeeasy.cloud.integration.appoint.AppointRuleManagerIntegrationService;
import com.zoeeasy.cloud.integration.appoint.validator.ParkingAppointRuleOperateRequestDtoValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预约规则管理集成服务
 *
 * @author walkman
 */
@Service(value = "appointRuleManagerIntegrationService")
@Slf4j
public class AppointRuleManagerIntegrationServiceImpl implements AppointRuleManagerIntegrationService {

    @Autowired
    private ParkingAppointRuleService parkingAppointRuleService;

    /**
     * 维护停车场预约规则
     *
     * @param requestDto
     * @return
     */
    @Override
    public ResultDto maintainParkingAppointRule(@FluentValid({ParkingAppointRuleOperateRequestDtoValidator.class}) ParkingAppointRuleUpdateRequestDto requestDto) {
        return parkingAppointRuleService.maintainParkingAppointRule(requestDto);
    }
}
