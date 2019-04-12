package com.zoeeasy.cloud.inspect.park;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
public interface InspectParkService {

    /**
     * 通过userId获取停车场
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkInspectorInfoResultDto> getParkingByUserId(ParkingByUserRequestDto requestDto);
}
