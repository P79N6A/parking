package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.VisitVehicleResultDto;

/**
 * @date: 2018/10/18.
 * @author：zm
 */
public interface VisitVehicleService {

    /**
     * 添加访客车
     *
     * @param requestDto
     * @return
     */
    ResultDto addVisitVehicle(VisitVehicleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除访客车
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteVisitVehicle(VisitVehicleDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改访客车
     *
     * @param requestDto
     * @return
     */
    ResultDto updateVisitVehicle(VisitVehicleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取访客车列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<VisitVehicleQueryPagedResultDto> getVisitVehiclePagedList(VisitVehicleQueryPagedRequestDto requestDto);

    /**
     * 获取访客车
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<VisitVehicleResultDto> getVisitVehicle(VisitVehicleGetRequestDto requestDto);
}
