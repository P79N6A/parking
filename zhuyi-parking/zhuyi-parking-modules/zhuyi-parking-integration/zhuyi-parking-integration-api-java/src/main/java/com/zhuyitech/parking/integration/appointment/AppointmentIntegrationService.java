package com.zhuyitech.parking.integration.appointment;


import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zhuyitech.parking.integration.appointment.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderGetRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointRefundAmountRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.*;
import com.zoeeasy.cloud.pms.platform.dto.result.AppointOrderDetailViewResultDto;

/**
 * 预约集成服务
 *
 * @author walkman
 */
public interface AppointmentIntegrationService {

    /**
     * 预约订单下单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderPlaceResultDto> placeOrder(UserAppointOrderPlaceRequestDto requestDto);

    /**
     * 预约订单确认
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointOrderConfirmResultDto> confirmOrder(AppointOrderConfirmParkingRequestDto requestDto);

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
    ObjectResultDto<AppointOrderCancelResultDto> cancelOrder(AppointOrderCancelParkingRequestDto requestDto);

    /**
     * 获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<AppointOrderViewResultDto> getOrderList(UserAppointOrderListGetRequestDto requestDto);

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderViewResultDto> getOrderPagedList(UserAppointOrderPagedResultRequestDto requestDto);

    /**
     * 后台分页根据月份获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderViewMonthResultDto> getOrderPagedListByMonth(UserAppointOrderPagedMonthRequestDto requestDto);

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
    ObjectResultDto<AppointOrderDetailViewResultDto> getOrderDetail(AppointOrderGetParkingRequestDto requestDto);

    /**
     * 预约订单支付判断
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCheckResultDto> payCheck(AppointOrderPayCheckParkingRequestDto requestDto);

    /**
     * 预约订单取消
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCancelCheckResultDto> cancelCheck(AppointOrderCancelCheckParkingRequestDto requestDto);
}
