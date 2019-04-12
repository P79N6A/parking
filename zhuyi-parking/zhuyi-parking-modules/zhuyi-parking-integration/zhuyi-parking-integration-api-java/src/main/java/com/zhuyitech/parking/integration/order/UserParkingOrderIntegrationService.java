package com.zhuyitech.parking.integration.order;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderListGetRequestDto;
import com.zhuyitech.parking.integration.order.dto.request.UserParkingOrderPagedResultRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;

/**
 * 用户停车订单服务
 *
 * @author walkman
 */
public interface UserParkingOrderIntegrationService {

    /**
     * 获取用户停车账单
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<ParkingOrderViewResultDto> getUserParkingOrderList(UserParkingOrderListGetRequestDto requestDto);

    /**
     * 分页获取用户停车账单
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingOrderViewResultDto> getUserParkingOrderPageList(UserParkingOrderPagedResultRequestDto requestDto);

    /**
     * 获取用户停车账单详情
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderDetailViewResultDto> getUserParkingOrderView(UserParkingOrderGetRequestDto requestDto);
}
