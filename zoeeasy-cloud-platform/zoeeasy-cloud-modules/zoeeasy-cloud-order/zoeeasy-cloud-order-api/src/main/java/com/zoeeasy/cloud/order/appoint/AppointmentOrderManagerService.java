package com.zoeeasy.cloud.order.appoint;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.scapegoat.infrastructure.exception.BusinessException;
import com.scapegoat.infrastructure.exception.ValidationException;
import com.zoeeasy.cloud.order.appoint.dto.request.*;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.ParkingAppointmentOrderResultDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderValidPlateRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.ParkingAppointOrderGetRequestDto;

/**
 * 预约停车订单服务
 *
 * @author walkman
 * @since 2018-04-01
 */
public interface AppointmentOrderManagerService {

    /**
     * 管理后台分页查询预约订单列表
     *
     * @param requestDto
     * @return
     */
    PagedResultDto<AppointOrderPageResultDto> getOrderPagedListAdmin(AppointOrderPagedRequestDto requestDto);

    /**
     * 后台分页获取预约订单列表
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<ParkingAppointmentOrderResultDto> getCustomerAppointOrderPagedList(CustomerAppointOrderPagedResultRequestDto requestDto);

    /**
     * 获取预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointmentOrderResultDto> getAppointOrder(ParkingAppointOrderGetRequestDto requestDto);

    /**
     * 预约订单入场
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto enterAppointOrder(AppointOrderEnterRequestDto requestDto);

    /**
     * 添加备注
     *
     * @param requestDto requestDto
     * @return
     */
    ResultDto addRemark(AppointOrderRemarkAddRequestDto requestDto) throws ValidationException, BusinessException;

    /**
     * 根据ID获取预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointmentOrderResultDto> getOrderByOrderNo(ParkingAppointOrderGetByIdRequestDto requestDto);

    /**
     * 带租户根据车牌获取有效的预约订单
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<ParkingAppointmentOrderResultDto> getValidOrderList(AppointOrderValidPlateRequestDto requestDto);

    /**
     * 带租户更新预约订单入场
     *
     * @param requestDto
     * @return
     */
    ResultDto updateEnterAppointOrder(AppointOrderUpdateEnterRequestDto requestDto);

    /**
     * 更新订单不带租户ID
     *
     * @param requestDto ParkingOrderUpdateForPayRequestDto
     * @return ResultDto
     */
    ResultDto updateAppointOrder(AppointOrderUpdateRequestDto requestDto);
}
