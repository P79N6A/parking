package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserAppointOrderListGetRequestDto", description = "获取用户预约订单列表请求参数")
public class UserAppointOrderListGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}
