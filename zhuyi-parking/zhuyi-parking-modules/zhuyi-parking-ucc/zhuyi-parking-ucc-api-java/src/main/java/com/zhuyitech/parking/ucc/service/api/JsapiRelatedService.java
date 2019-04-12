package com.zhuyitech.parking.ucc.service.api;


import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderGetRequestDto;
import com.zhuyitech.parking.ucc.dto.request.jsapi.JsapiParkingOrderPagedRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderDetailViewResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderViewResultDto;

/**
 * 公众号相关服务
 *
 * @author zwq
 */
public interface JsapiRelatedService {


    /**
     * 分页获取用户停车账单
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageList(JsapiParkingOrderPagedRequestDto requestDto);

    PagedResultDto<ParkingOrderViewResultDto> getParkingOrderPageListApplication(JsapiParkingOrderPagedRequestDto requestDto);

    /**
     * 获取用户停车账单详情
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderDetailViewResultDto> getParkingOrderView(JsapiParkingOrderGetRequestDto requestDto);
}
