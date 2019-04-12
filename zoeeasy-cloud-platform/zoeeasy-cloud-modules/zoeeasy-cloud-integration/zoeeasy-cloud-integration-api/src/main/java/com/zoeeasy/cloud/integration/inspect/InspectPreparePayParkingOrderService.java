package com.zoeeasy.cloud.integration.inspect;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.zoeeasy.cloud.integration.order.dto.request.ParkingOrderPreparePaymentRequestDto;
import com.zoeeasy.cloud.integration.order.dto.result.ParkingOrderPreparePaymentResultDto;

/**
 * @author AkeemSuper
 * @date 2019/1/7 0007
 */
public interface InspectPreparePayParkingOrderService {

    /**
     * 停车账单准备支付(带租户)
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ObjectResultDto<ParkingOrderPreparePaymentResultDto> preparePayParkingOrderInspect(ParkingOrderPreparePaymentRequestDto requestDto);
}
