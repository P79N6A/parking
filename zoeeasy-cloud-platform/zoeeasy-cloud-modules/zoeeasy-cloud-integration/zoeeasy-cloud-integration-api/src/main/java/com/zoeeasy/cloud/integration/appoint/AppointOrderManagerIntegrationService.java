package com.zoeeasy.cloud.integration.appoint;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.PagedResultDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderCancelRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderGetDetailRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.request.AppointOrderPagedRequestDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderCancelResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderGetDetailResultDto;
import com.zoeeasy.cloud.order.appoint.dto.result.AppointOrderPageResultDto;

/**
 * 预约管理集成服务
 *
 * @author zwq
 */
public interface AppointOrderManagerIntegrationService {

    /**
     * 预约订单条件查询
     *
     * @param requestDto requestDto
     * @return
     */
    PagedResultDto<AppointOrderPageResultDto> getOrderPagedList(AppointOrderPagedRequestDto requestDto);

    /**
     * 预约订单详情
     *
     * @param requestDto requestDto
     * @return
     */
    ObjectResultDto<AppointOrderGetDetailResultDto> getOrderDetail(AppointOrderGetDetailRequestDto requestDto);

    /**
     * 取消预约订单
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<AppointOrderCancelResultDto> cancelAppointOrder(AppointOrderCancelRequestDto requestDto);

}
