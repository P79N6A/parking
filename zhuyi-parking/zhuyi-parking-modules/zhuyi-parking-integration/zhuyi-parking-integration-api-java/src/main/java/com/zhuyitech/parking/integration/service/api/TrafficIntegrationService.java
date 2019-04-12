package com.zhuyitech.parking.integration.service.api;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.PushTrafficLimitRequestDto;
import com.zhuyitech.parking.tool.dto.request.trafficrestriction.TrafficRestrictionRemindRequestDto;
import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionRemindResultDto;

/**
 * 限行集成服务
 *
 * @author walkman
 */
public interface TrafficIntegrationService {

    /**
     * 尾号限行提醒推送
     *
     * @param requestDto PushTrafficLimitRequestDto
     * @return ResultDto
     */
    ResultDto pushTrafficLimit(PushTrafficLimitRequestDto requestDto);

    /**
     * 尾号限行提醒
     *
     * @param requestDto requestDto
     * @return PagedResultDto
     */
    PagedResultDto<TrafficRestrictionRemindResultDto> trafficRestrictionTailNumberRemind(TrafficRestrictionRemindRequestDto requestDto);
}
