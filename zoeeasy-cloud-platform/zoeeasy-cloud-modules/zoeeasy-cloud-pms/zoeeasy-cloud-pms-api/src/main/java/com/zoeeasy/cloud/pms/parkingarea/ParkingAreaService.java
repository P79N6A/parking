package com.zoeeasy.cloud.pms.parkingarea;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.parkingarea.dto.request.*;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaListResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaQueryPagedResultDto;
import com.zoeeasy.cloud.pms.parkingarea.dto.result.ParkingAreaResultDto;

/**
 * 泊位区域管理服务
 * Created by song on 2018/9/21.
 */
public interface ParkingAreaService {

    /**
     * 添加泊位区域
     *
     * @param requestDto
     * @return
     */
    ResultDto addParkingArea(ParkingAreaAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除泊位区域
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingArea(ParkingAreaDeleteRequestDto requestDto);

    /**
     * 修改泊位区域
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingArea(ParkingAreaUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 分页获取泊位区域
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingAreaQueryPagedResultDto> getParkingAreaPagedList(ParkingAreaQueryPagedRequestDto requestDto);

    /**
     * 获取泊位区域
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAreaResultDto> getParkingArea(ParkingAreaGetRequestDto requestDto);

    /**
     * 获取泊车区域列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingAreaListResultDto> getParkingAreaList(ParkingAreaListRequestDto requestDto);


    /**
     * 获取泊位区域
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAreaResultDto> getParkingArea(ParkingAreaMyGetRequestDto requestDto);


    /**
     * 获取泊位区域
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingAreaResultDto> getParkingAreaInfo(ParkingAreaGetRequestDto requestDto);



}
