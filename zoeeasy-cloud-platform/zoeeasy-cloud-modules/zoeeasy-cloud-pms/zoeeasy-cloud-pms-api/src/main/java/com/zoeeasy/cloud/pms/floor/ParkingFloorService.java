package com.zoeeasy.cloud.pms.floor;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.floor.dto.request.*;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorListResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorPagedResultDto;
import com.zoeeasy.cloud.pms.floor.dto.result.FloorResultDto;

/**
 * Created by song on 2019/3/23 9:58
 */
public interface ParkingFloorService {

    /**
     * 添加楼层
     *
     * @param requestDto
     * @return
     */
    ResultDto addFloor(FloorAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改楼层
     *
     * @param requestDto
     * @return
     */
    ResultDto updateFloor(FloorUpdateRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 获取楼层详情
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<FloorResultDto> getFloor(FloorGetRequestDto requestDto);

    /**
     * 删除楼层
     *
     * @param requestDto
     * @return
     */
    ResultDto deleteFloor(FloorGetRequestDto requestDto);

    /**
     * 分页获取楼层
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<FloorPagedResultDto> getFloorPagedList(FloorPagedRequestDto requestDto);

    /**
     * 获取楼层列表
     *
     * @param requestDto
     * @return
     */
    ListResultDto<FloorListResultDto> getFloorList(FloorListRequestDto requestDto);

}
