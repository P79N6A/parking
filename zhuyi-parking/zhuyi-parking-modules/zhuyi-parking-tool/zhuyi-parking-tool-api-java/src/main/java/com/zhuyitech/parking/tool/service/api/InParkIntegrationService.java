package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkOrderUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.request.inpark.InParkParkingUrlGetRequestDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkOrderUrlResultDto;
import com.zhuyitech.parking.tool.dto.result.inpark.InParkParkingUrlResultDto;

/**
 * Inpark对接集成服务
 *
 * @author walkman
 */
public interface InParkIntegrationService {

    /**
     * @param requestDto
     * @return
     */
    ObjectResultDto<InParkParkingUrlResultDto> getParkingListUrl(InParkParkingUrlGetRequestDto requestDto);

    /**
     * @param requestDto
     * @return
     */
    ObjectResultDto<InParkOrderUrlResultDto> getOrderListUrl(InParkOrderUrlGetRequestDto requestDto);
}
