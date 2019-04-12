package com.zoeeasy.cloud.integration.appoint;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderListGetRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedMonthRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPagedResultRequestDto;
import com.zoeeasy.cloud.integration.appoint.dto.request.CustomerAppointOrderPlaceRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.result.AppointOrderDetailViewResultDto;

/**
 * 预约集成服务
 *
 * @author walkman
 */
public interface AppointOrderPlatformIntegrationService {

    /**
     * 预约订单下单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderPlaceResultDto> placeOrder(CustomerAppointOrderPlaceRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 预约订单确认
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointOrderConfirmResultDto> confirmOrder(AppointOrderConfirmRequestDto requestDto);

    /**
     * 计算预约取消退款金额
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointRefundAmountResultDto> calculateRefundAmount(AppointRefundAmountRequestDto requestDto);

    /**
     * 预约订单取消
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCancelResultDto> cancelOrder(AppointOrderCancelRequestDto requestDto);

    /**
     * 获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<AppointOrderViewResultDto> getOrderList(CustomerAppointOrderListGetRequestDto requestDto);

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderViewResultDto> getOrderPagedList(CustomerAppointOrderPagedResultRequestDto requestDto);

    /**
     * 后台分页根据月份获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderViewMonthResultDto> getOrderPagedListByMonth(CustomerAppointOrderPagedMonthRequestDto requestDto);

    /**
     * 获取预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderViewResultDto> getOrder(AppointOrderGetRequestDto requestDto);

    /**
     * 获取预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderDetailViewResultDto> getOrderDetail(AppointOrderGetRequestDto requestDto);

    /**
     * 预约订单支付判断
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCheckResultDto> payCheck(ParkingAppointOrderPayCheckRequestDto requestDto);

    /**
     * 预约订单判断
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCancelCheckResultDto> cancelCheck(AppointOrderCancelCheckRequestDto requestDto);


    /**
     * 云平台获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderPageResultDto> getOrderPagedListAdmin(AppointOrderPagedRequestDto requestDto);

    /**
     * MQ关闭预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto closeAppointOrder(AppointOrderCloseRequestDto requestDto);

    /**
     * MQ处理支付记录
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto tradePaymentRecord(TradePaymentRecordRequestDto requestDto);
}
