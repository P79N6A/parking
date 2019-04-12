package com.zoeeasy.cloud.pms.charge;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingChargeInfoGetByParkingIdRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingChargeInfoResultDto;

/**
 * @author AkeemSuper
 * @date 2018/11/22 0022
 */
public interface ParkingChargeInfoService {

    /**
     * 通过停车场id获取停车场收费信息
     *
     * @param requestDto ParkingChargeInfoGetByParkingIdRequestDto
     * @return ObjectResultDto<ParkingChargeInfoResultDto>
     */
    ObjectResultDto<ParkingChargeInfoResultDto> getParkChargeInfoByParkingId(ParkingChargeInfoGetByParkingIdRequestDto requestDto);
}
