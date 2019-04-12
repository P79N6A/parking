package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.ApplyParkingPagedRequestDto;
import com.zoeeasy.cloud.pms.park.dto.request.AuditParkingRequestDto;
import com.zoeeasy.cloud.pms.park.dto.result.ApplyParkingPagedResultDto;

/**
 * 停车场审核服务
 */
public interface ParkingApplyInfoService {

    /**
     * 审核停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto auditParking(AuditParkingRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取审核停车场列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ApplyParkingPagedResultDto> getApplyParkingPagedList(ApplyParkingPagedRequestDto requestDto);

}
