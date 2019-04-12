package com.zoeeasy.cloud.order.inspect;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectGetParkingOrderListRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetPlateOrderNoRequestDto;
import com.zoeeasy.cloud.order.parking.dto.request.ParkingOrderGetRequestDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;

/**
 * 巡检平台订单服务
 *
 * @author AkeemSuper
 * @since 2018/9/30 0030
 */
public interface InspectParkingOrderService {

    /**
     * 分页获取巡检停车场停车订单
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(InspectGetParkingOrderListRequestDto requestDto);

    /**
     * 更新未支付订单支付渠道
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    ResultDto updateParkingOrder(InspectUpdateParkingOrderRequestDto requestDto);

    /**
     * 获取停车账单
     *
     * @param requestDto ParkingOrderGetRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrder(ParkingOrderGetRequestDto requestDto);

    /**
     * 根据车牌号订单号获取停车订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderResultDto> getByPlateOrderNoInspect(ParkingOrderGetPlateOrderNoRequestDto requestDto);
}
