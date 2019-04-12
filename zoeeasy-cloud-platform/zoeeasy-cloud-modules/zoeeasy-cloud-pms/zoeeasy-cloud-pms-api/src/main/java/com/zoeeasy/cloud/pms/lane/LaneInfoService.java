package com.zoeeasy.cloud.pms.lane;

import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.pms.lane.dto.request.AddParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.request.UpdateParkingLaneRequestDto;
import com.zoeeasy.cloud.pms.lane.dto.result.AddParkingLaneResultDto;
import com.zoeeasy.cloud.pms.lane.dto.result.UpdateParkingLaneResultDto;

/**
 * @Date: 2018/11/30
 * @author: lhj
 */
public interface LaneInfoService {

    /**
     * 添加车道
     *
     * @param requestDto
     * @return
     */
    AddParkingLaneResultDto addParkingLane(AddParkingLaneRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 修改车道
     *
     * @param requestDto
     * @return
     */
    UpdateParkingLaneResultDto updateParkingLane(UpdateParkingLaneRequestDto requestDto) throws ValidationException, BusinessException;
}
