package com.zoeeasy.cloud.pms.garage;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.garage.dto.request.*;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageListGetResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageQueryPagedResultDto;
import com.zoeeasy.cloud.pms.garage.dto.result.GarageResultDto;

/**
 * 车库管理服务
 * Created by song on 2018/9/20.
 */
public interface ParkingGarageService {

    /**
     * 添加车库
     *
     * @param requestDto
     * @return
     */
    ResultDto addGarage(GarageAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改车库
     *
     * @param requestDto
     * @return
     */
    ResultDto updateGarage(GarageUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除车库
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteGarage(GarageDeleteRequestDto requestDto);

    /**
     * 获取车库
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<GarageResultDto> getGarage(GarageGetRequestDto requestDto);

    /**
     * 分页获取车库列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<GarageQueryPagedResultDto> getGaragePagedList(GarageQueryPagedRequestDto requestDto);

    /**
     * 获取车库列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<GarageListGetResultDto> getGarageList(GarageListGetRequestDto requestDto);

}
