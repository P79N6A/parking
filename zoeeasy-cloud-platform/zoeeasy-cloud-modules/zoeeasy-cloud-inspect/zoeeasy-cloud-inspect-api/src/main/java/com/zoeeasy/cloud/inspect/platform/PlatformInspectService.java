package com.zoeeasy.cloud.inspect.platform;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkCollectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByParkingIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.request.ParkInspectorGetByUserIdRequestDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkCollectorInfoResultDto;
import com.zoeeasy.cloud.inspect.platform.dto.result.ParkInspectorInfoResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/5 0005
 */
public interface PlatformInspectService {

    /**
     * 通过停车场获取巡检id
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkInspectorInfoResultDto> getParkInspectorByParkingId(ParkInspectorGetByParkingIdRequestDto requestDto);

    /**
     * 通过停车场获取收费员id
     *
     * @param requestDto
     * @return
     */
    ListResultDto<ParkCollectorInfoResultDto> getParkCollectorByParkingId(ParkCollectorGetByParkingIdRequestDto requestDto);

    /**
     * 通过userId获取巡检
     */
    ObjectResultDto<ParkInspectorInfoResultDto> getParkInspectorByUserId(ParkInspectorGetByUserIdRequestDto requestDto);

    /**
     * 通过userId获取收费员
     */
    ObjectResultDto<ParkCollectorInfoResultDto> getParkCollectorByUserId(ParkCollectorGetByUserIdRequestDto requestDto);


}
