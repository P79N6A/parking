package com.zoeeasy.cloud.pms.specialvehicle;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.specialvehicle.dto.request.*;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleQueryPagedResultDto;
import com.zoeeasy.cloud.pms.specialvehicle.dto.result.FixedVehicleResultDto;

/**
 * 固定车
 *
 * @date: 2018/10/17.
 * @author：zm
 */
public interface FixedVehicleService {

    /**
     * 添加固定车
     *
     * @param requestDto
     * @return
     */
    ResultDto addFixedVehicle(FixedVehicleAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除固定车
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteFixedVehicle(FixedVehicleDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 关联泊位
     *
     * @param requestDto
     */
    ResultDto relevanceParkingLotId(RelevanceParkingLotIdRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改固定车
     *
     * @param requestDto
     * @return
     */
    ResultDto updateFixedVehicle(FixedVehicleUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取固定车详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<FixedVehicleResultDto> getFixedVehicle(FixedVehicleGetRequestDto requestDto);

    /**
     * 分页获取固定车
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<FixedVehicleQueryPagedResultDto> getFixedVehiclePagedList(FixedVehicleQueryPagedRequestDto requestDto);

}
