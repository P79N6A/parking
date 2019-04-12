package com.zoeeasy.cloud.integration.open;

import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.integration.open.dto.request.AnyParkingOrderPlaceRequestDto;
import com.zoeeasy.cloud.integration.open.dto.request.CloudOrderCallbackRequestDto;

/**
 * 客户端订单服务
 *
 * @author AkeemSuper
 * @since 2018/12/3 0003
 */
public interface TerminateParkingOrderService {

    /**
     * 客戶端下單
     *
     * @param requestDto
     * @return
     */
    ResultDto placeAynParkingOrder(AnyParkingOrderPlaceRequestDto requestDto);

    /**
     * 账单回调接口
     *
     * @param requestDto
     * @return
     */
    ResultDto orderCallback(CloudOrderCallbackRequestDto requestDto);

}
