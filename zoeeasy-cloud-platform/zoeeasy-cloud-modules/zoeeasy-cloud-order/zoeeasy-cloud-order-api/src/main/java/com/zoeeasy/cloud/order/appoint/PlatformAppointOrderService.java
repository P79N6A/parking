package com.zoeeasy.cloud.order.appoint;

import com.scapegoat.infrastructure.core.dto.result.ListResultDto;
import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCreateResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderHadResultDto;


/**
 * 平台订单服务
 *
 * @author AkeemSuper
 * @date 2018/11/1 0001
 */
public interface PlatformAppointOrderService {

    /**
     * 获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    ListResultDto<ParkingAppointmentOrderResultDto> getAppointOrderList(ParkingAppointOrderListGetRequestDto requestDto);

    /**
     * 分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingAppointmentOrderResultDto> getAppointOrderPagedList(ParkingAppointOrderPagedResultRequestDto requestDto);

    /**
     * 创建预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCreateResultDto> createAppointOrder(AppointOrderCreateRequestDto requestDto);

    /**
     * 根据车牌获取有效的预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointmentOrderResultDto> getValidOrderList(AppointOrderValidPlateRequestDto requestDto);

    /**
     * 更新预约订单入场
     *
     * @param requestDto
     * @return
     */
    ResultDto updateEnterAppointOrder(AppointOrderUpdateEnterRequestDto requestDto);

    /**
     * 判断用户是否存在有效预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderHadResultDto> getAppointOrderByCustomerUserId(ParkingAppointOrderGetByCustomerRequestDto requestDto);

    /**
     * 根据orderNo更新预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto updateAppointOrderByOrderNo(AppointOrderUpdateByOrderNoRequestDto requestDto);

    /**
     * 获取预约订单(无talentId)
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointmentOrderResultDto> getAppointOrderApp(ParkingAppointOrderGetRequestDto requestDto);

    /**
     * 超时未支付取消订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto closePayTimeoutOrder(AppointOrderPayTimeoutCloseRequestDto requestDto);

    /**
     * 超时未进场取消订单
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto closeEnterTimeoutOrder(AppointOrderEnterTimeoutCloseRequestDto requestDto);

}
