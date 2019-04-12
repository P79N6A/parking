package com.zoeeasy.cloud.pms.lane;

import com.scapegoat.infrastructure.core.dto.basic.ComboboxItemDto;
import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.lane.dto.request.*;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneListGetResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneQueryPagedResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.ParkingLaneResultDto;

/**
 * 停车场车道管理服务
 */
public interface ParkingLaneInfoService {
    /**
     * 新增停车场车道
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto addParkingLane(ParkingLaneAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 删除停车场车道
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteParkingLane(ParkingLaneDeleteRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 更新停车场车道
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingLane(ParkingLaneUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取停车场车道
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingLaneResultDto> getParkingLane(ParkingLaneGetRequestDto requestDto);

    /**
     * 获取停车场车道列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingLaneListGetResultDto> getParkingLaneList(ParkingLaneListGetRequestDto requestDto);

    /**
     * 分页获取停车场车道列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingLaneQueryPagedResultDto> getParkingLanePagedList(ParkingLaneQueryPagedResultRequestDto requestDto);

    /**
     * 批量删除停车场进出口
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteBatchParkingLane(ParkingLaneBatchDeleteRequestDto requestDto);

    /**
     * 批量导入
     *
     * @param bytes
     * @param fileName
     * @return
     */
    ResultDto importExcel(byte[] bytes, String fileName);

    /**
     * 获取车道类型
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ComboboxItemDto> getParkingLaneType(ParkingLaneTypeGetRequestDto requestDto);
}
