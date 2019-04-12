package com.zoeeasy.cloud.integration.appoint;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.charge.appointrule.dto.request.ParkingAppointRuleUpdateRequestDto;

/**
 * 预约规则管理集成服务
 *
 * @author walkman
 */
public interface AppointRuleManagerIntegrationService {

    /**
     * 维护停车场预约规则
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto maintainParkingAppointRule(ParkingAppointRuleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

}
