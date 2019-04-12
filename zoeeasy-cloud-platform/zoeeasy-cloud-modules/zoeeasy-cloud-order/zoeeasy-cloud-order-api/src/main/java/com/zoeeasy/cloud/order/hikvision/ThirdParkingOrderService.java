package com.zoeeasy.cloud.order.hikvision;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.request.*;
import com.zoeeasy.cloud.order.hikvision.dto.result.FindThirdOrderByBillNoResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderPlaceResultDto;
import com.zoeeasy.cloud.order.hikvision.dto.result.HikvisionParkingOrderResultDto;

/**
 * 海康平台订单服务
 *
 * @author AkeemSuper
 * @since 2018/9/30 0030
 */
public interface ThirdParkingOrderService {

    /**
     * 海康平台订单请求并保存到本地
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<HikvisionParkingOrderPlaceResultDto> placeHikvisionParkingOrder(HikvisionParkingOrderPlaceRequestDto requestDto);

    /**
     * 海康平台订单确认
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto completeHikvisionParkingOrder(HikvisionPaymentCompleteRequestDto requestDto);

    /**
     * 过车数据处理--通过平台订单号
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<HikvisionParkingOrderResultDto> getByOrderNo(HikvisionParkingOrderGetByOrderNoRequestDto requestDto);

    /**
     * 通过billNo查询
     */
    ObjectResultDto<FindThirdOrderByBillNoResultDto> findByBillNo(FindThirdOrderByBillNoRequestDto requestDto);

    /**
     * 保存任意停车平台订单
     *
     * @param requestDto
     * @return
     */
    ResultDto saveAnyParkingOrder(AnyParkingOrderSaveRequestDto requestDto);
}
