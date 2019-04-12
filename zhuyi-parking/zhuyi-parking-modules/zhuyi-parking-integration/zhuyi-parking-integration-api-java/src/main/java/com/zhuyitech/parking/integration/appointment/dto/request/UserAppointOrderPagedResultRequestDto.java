package com.zhuyitech.parking.integration.appointment.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页获取用户预约订单列表请求参数
 *
 * @author walkman
 * @since 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "UserAppointOrderPagedResultRequestDto", description = "分页获取用户预约订单列表请求参数")
public class UserAppointOrderPagedResultRequestDto extends SessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

}
