package com.zoeeasy.cloud.integration.order;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderAddRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderDetailGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderSearchRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchDetailResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderSearchResultDto;

/**
 * 商户订单管理集成服务
 *
 * @author walkman
 */
public interface ParkingOrderManagerIntegrationService {

    /**
     * 网页查询获取停车账单
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderSearchResultDto>
     */
    PagedResultDto<ParkingOrderSearchResultDto> getParkingOrderPageList(ParkingOrderSearchRequestDto requestDto);

    /**
     * 网页查询获取账单详情
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<ParkingOrderSearchDetailResultDto>
     */
    ObjectResultDto<ParkingOrderSearchDetailResultDto> getParkingOrderSearchDetailView(ParkingOrderDetailGetRequestDto requestDto);

    /**
     * 人工添加停车账单
     *
     * @param requestDto ParkingOrderAddRequestDto
     * @return ResultDto
     */
    ResultDto addParkingOrder(ParkingOrderAddRequestDto requestDto);
}
