package com.zoeeasy.cloud.pms.park;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.pms.platform.dto.request.ParkingLotDecreaseRequestDto;

/**
 * 车位状态服务
 *
 * @author AkeemSuper
 * @date 2018/9/26 0026
 */
public interface ParkingLotStatusService {

    /**
     * 变更停车场可用车位数及变更车位状态占用
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto decreaseParkingLotAvailable(ParkingLotDecreaseRequestDto requestDto);
}
