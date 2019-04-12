package com.zoeeasy.cloud.integration.order;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderDetailGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderListGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderQueryByPlatePageRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderQueryJsapiPageRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;

/**
 * 车主用户订单集成服务
 *
 * @author wangfeng
 * @date 2018/10/31 16:55
 **/
public interface CustomerParkingOrderIntegrationService {

    /**
     * 分页获取停车账单
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderViewResultDto>
     */
    PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageList(ParkingOrderQueryByPlatePageRequestDto requestDto);

    /**
     * 获取停车账单
     *
     * @param requestDto requestDto
     * @return ListResultDto<ParkingOrderViewResultDto>
     */
    ListResultDto<ParkingOrderViewResultDto> getParkingOrderList(ParkingOrderListGetRequestDto requestDto);

    /**
     * 获取停车账单详情
     *
     * @param requestDto requestDto
     * @return ObjectResultDto<ParkingOrderDetailViewResultDto>
     */
    ObjectResultDto<ParkingOrderDetailViewResultDto> getParkingOrderDetailView(ParkingOrderDetailGetRequestDto requestDto);

    /**
     * 分页获取停车账单Jsapi
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderViewResultDto>
     */
    PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListJsapiApplication(ParkingOrderQueryJsapiPageRequestDto requestDto);

    PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListJsapi(ParkingOrderQueryJsapiPageRequestDto requestDto);

}
