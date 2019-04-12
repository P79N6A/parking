package com.zhuyitech.parking.data.service.api;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.dto.result.ParkingRecordResultDto;


/**
 * 平台停车记录服务
 *
 * @author walkman
 */
public interface ParkingRecordService {

    /**
     * 分页查询停车记录
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<ParkingRecordResultDto> getParkingRecordPageList(PagedResultRequestDto requestDto);

}
