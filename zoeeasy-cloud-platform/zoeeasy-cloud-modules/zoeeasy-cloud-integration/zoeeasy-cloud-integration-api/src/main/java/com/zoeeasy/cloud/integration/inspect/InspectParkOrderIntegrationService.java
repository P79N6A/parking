package com.zoeeasy.cloud.integration.inspect;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectParkingOrderListPageRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.request.InspectUpdateParkingOrderRequestDto;
import com.zoeeasy.cloud.order.inspect.dto.result.InspectParkingOrderResultDto;

/**
 * 巡检订单集成服务
 *
 * @author zwq
 * @date 2018/11/20 16:55
 **/
public interface InspectParkOrderIntegrationService {

    /**
     * 分页获取停车账单
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderViewResultDto>
     */
    PagedResultDto<InspectParkingOrderResultDto> getParkingOrderPageList(InspectParkingOrderListPageRequestDto requestDto);

    /**
     * 停车账单支付
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto payParkingOrderInspect(InspectUpdateParkingOrderRequestDto requestDto);

}
