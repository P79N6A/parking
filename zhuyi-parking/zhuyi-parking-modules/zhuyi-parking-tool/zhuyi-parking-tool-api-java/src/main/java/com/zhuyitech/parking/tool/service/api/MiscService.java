package com.zhuyitech.parking.tool.service.api;


import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.tool.dto.request.parking.ParkingInfoGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.parking.ParkingInfoBaseResultDto;

/**
 * 其他服务
 *
 * @author walkman
 * @date 2018/4/29
 */
public interface MiscService {

    /**
     * @return
     */
    PagedResultDto<ParkingInfoBaseResultDto> getParking(ParkingInfoGetRequestDto prams);
}
