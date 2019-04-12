package com.zoeeasy.cloud.order.parking;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.parking.dto.request.*;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderCreateResultDto;
import com.zoeeasy.cloud.order.parking.dto.result.ParkingOrderResultDto;

/**
 * 平台订单服务
 *
 * @author walkman
 * @since 2018/10/7 0007
 */
public interface PlatformParkingOrderService {

    /**
     * 分页获取用户停车订单
     *
     * @param requestDto requestDto
     * @return PagedResultDto<ParkingOrderResultDto>
     */
    PagedResultDto<ParkingOrderResultDto> getParkingOrderPageList(ParkingOrderQueryByPlatePageRequestDto requestDto);

    /**
     * 获取用户停车订单列表
     *
     * @param requestDto requestDto
     * @return ListResultDto<ParkingOrderResultDto>
     */
    ListResultDto<ParkingOrderResultDto> getParkingOrderList(ParkingOrderListGetRequestDto requestDto);

    /**
     * 保存订单
     *
     * @param requestDto ParkingOrderSaveRequestDto
     * @return ObjectResultDto<ParkingOrderSaveResultDto>
     */
    ObjectResultDto<ParkingOrderCreateResultDto> saveParkingOrder(ParkingOrderCreateRequestDto requestDto);

    /**
     * 更新订单
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    ResultDto updateParkingOrder(ParkingOrderPayStatusUpdateRequestDto requestDto);

    /**
     * 停车订单准备支付更新
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto updateParkingOrder(ParkingOrderPayingUpdateRequestDto requestDto);

    /**
     * 根据订单号车牌号更新订单
     *
     * @param requestDto
     * @return
     */
    ResultDto updateParkingOrderByPlatNumber(ParkingOrderUpdateByPlateNumberRequestDto requestDto);

    /**
     * 根据单号获取订单信息
     *
     * @param requestDto ParkingOrderGetByOrderNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getByOrderNo(ParkingOrderGetByOrderNoRequestDto requestDto);

    /**
     * 根据车牌号订单号获取停车订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<ParkingOrderResultDto> getByPlateOrderNo(ParkingOrderGetPlateOrderNoRequestDto requestDto);

    /**
     * 根据停车记录号获取订单信息
     *
     * @param requestDto ParkingOrderGetByRecordNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrderByRecordNo(ParkingOrderGetByRecordNoRequestDto requestDto);

    /**
     * 根据停车记录号车牌号获取订单信息
     *
     * @param requestDto ParkingOrderGetByRecordNoRequestDto
     * @return ObjectResultDto<ParkingOrderResultDto>
     */
    ObjectResultDto<ParkingOrderResultDto> getParkingOrderByRecordNoAndPlate(ParkingOrderGetByRecordNoPlateRequestDto requestDto);

    /**
     * 修改停车订单支付状态
     *
     * @param requestDto requestDto
     * @return ResultDto
     */
    ResultDto updatePayStatus(ParkingOrderPayedUpdateRequestDto requestDto);

    /**
     * 更新账单结算状态
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateSettleRecord(ParkingOrderSettleUpdateRequestDto requestDto);
}
