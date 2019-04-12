package com.zoeeasy.cloud.pms.appoint;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingAppointInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingAppointInfoResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/22 0022
 */
public interface ParkingAppointmentInfoService {

    /**
     * 根据parkingId获取停车场预约信息
     *
     * @param requestDto ParkingAppointInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingAppointInfoResultDto>
     */
    ObjectResultDto<ParkingAppointInfoResultDto> getAppointmentInfoByParkingId(ParkingAppointInfoGetByParkingIdRequestDto requestDto);
}
