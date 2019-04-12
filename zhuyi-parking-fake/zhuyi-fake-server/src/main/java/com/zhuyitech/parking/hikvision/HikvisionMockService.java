package com.zhuyitech.parking.hikvision;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.hikvision.dto.MockHikPassingVehicleRequestDto;

public interface HikvisionMockService {

    /**
     * 海康过车数据模拟
     *
     * @return
     */
    ResultDto mockHikPassingVehicle();

    /**
     * 模拟海康平台过车数据
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto mockHikvisionVehicleRecord(MockHikPassingVehicleRequestDto requestDto);
}
