package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.driver.LicenseScoreQueryRequestDto;
import com.zhuyitech.parking.tool.dto.result.carpoint.LicensePointPenaltyDataResultDto;

/**
 * 驾驶证扣分查询服务
 *
 * @Date: 2018/4/12
 * @author: yuzhicheng
 */
public interface LicensePointService {

    /**
     * 驾驶证扣分查询
     *
     * @param requestDto 驾驶证扣分查询请求参数
     * @return ObjectResultDto
     */
    ObjectResultDto<LicensePointPenaltyDataResultDto> requestCarPointPenaltyData(LicenseScoreQueryRequestDto requestDto);




}
