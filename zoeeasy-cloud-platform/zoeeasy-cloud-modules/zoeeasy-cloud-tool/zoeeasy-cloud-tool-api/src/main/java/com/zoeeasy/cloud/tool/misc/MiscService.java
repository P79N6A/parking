package com.zoeeasy.cloud.tool.misc;

import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.tool.misc.dto.request.ParkingInfoGetRequestDto;
import com.zoeeasy.cloud.tool.misc.dto.response.ParkingInfoBaseResultDto;

/**
 * 停车场数据拉取
 *
 * @author wangfeng
 * @date 2018/11/23 9:42
 **/
public interface MiscService {
    /**
     * 从api-拉取停车场数据
     *
     * @param requestDto MiscParkingInfoGetRequestDto
     * @return MiscParkingInfoBaseResultDto
     */
    PagedResultDto<ParkingInfoBaseResultDto> fetchNationalParkingInfo(ParkingInfoGetRequestDto requestDto);
}