package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.park.dto.request.*;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingPagedResultDto;
import com.zoeeasy.cloud.pms.park.dto.result.ManagementParkingResultDto;

/**
 * 管理端停车场服务
 */
public interface ManagementParkingInfoService {

    /**
     * 添加第三方停车场
     *
     * @return
     */
    ResultDto addThirdPartyParking(ThirdPartyParkingAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改第三方停车场
     *
     * @param requestDto
     * @return
     */
    ResultDto updateThirdPartyParking(ThirdPartyParkingUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取停车场详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ManagementParkingResultDto> getManagementParking(ParkingGetRequestDto requestDto);

    /**
     * 删除停车场(无租户)
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteManagementParking(ParkingDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页查询已审核通过停车场
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ManagementParkingPagedResultDto> getManagementParkingPagedList(ManagementParkingPagedRequestDto requestDto);

    /**
     * 停车场上下架
     *
     * @param requestDto
     * @return
     */
    ResultDto parkingPutAway(ParkingPutAwayRequestDto requestDto) throws ValidationException, BusinessException;

}
