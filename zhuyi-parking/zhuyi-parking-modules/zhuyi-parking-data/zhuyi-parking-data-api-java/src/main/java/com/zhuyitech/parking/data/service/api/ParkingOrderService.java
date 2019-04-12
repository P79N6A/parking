package com.zhuyitech.parking.data.service.api;

import com.scapegoat.infrastructure.core.dto.request.PagedResultRequestDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.data.dto.result.order.ParkingOrderResultDto;

/**
 * 停车订单服务
 *
 * @author walkman
 */
public interface ParkingOrderService {

    /**
     * 分页获取停车账单
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingOrderResultDto> getCloudParkingOrderPageList(PagedResultRequestDto requestDto);

}

