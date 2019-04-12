package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingQrcodeGetRequestDto;
import com.zoeeasy.cloud.pms.platform.dto.result.ParkingQrcodeResultDto;

/**
 * @author zwq
 * @date 2018/12/21 0022
 */
public interface ParkingQrcodeService {

    /**
     * 通过noncestr获取parkingId
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<ParkingQrcodeResultDto>
     */
    ObjectResultDto<ParkingQrcodeResultDto> getParkingQrcode(ParkingQrcodeGetRequestDto requestDto);
}
