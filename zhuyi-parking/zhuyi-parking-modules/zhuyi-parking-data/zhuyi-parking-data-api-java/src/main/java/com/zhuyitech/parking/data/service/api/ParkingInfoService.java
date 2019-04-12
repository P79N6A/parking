package com.zhuyitech.parking.data.service.api;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.dto.result.park.ParkingResultDto;

/**
 * 停车场管理服务
 *
 * @author walkman
 * @date 2017-12-11
 */
public interface ParkingInfoService {
    /**
     * 分页获取停车场列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingResultDto> getParkingPagedList(PagedResultRequestDto requestDto);
}
