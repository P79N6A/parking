package com.zoeeasy.cloud.integration.inspect;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.zoeeasy.cloud.inspect.park.dto.request.ParkingByUserRequestDto;
import com.zoeeasy.cloud.inspect.park.dto.result.ParkingByUserResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
public interface InspectParkIntegrationService {

    /**
     * 通过userId获取停车场
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkingByUserResultDto> getParkingByUserId(ParkingByUserRequestDto requestDto);
}
