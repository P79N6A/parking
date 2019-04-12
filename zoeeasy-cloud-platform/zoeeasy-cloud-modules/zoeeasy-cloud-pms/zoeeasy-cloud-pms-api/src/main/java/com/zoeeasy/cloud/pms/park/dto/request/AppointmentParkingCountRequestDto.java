package com.zoeeasy.cloud.pms.park.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 可预约停车场数量请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointmentParkingCountRequestDto", description = "可预约停车场数量请求参数")
public class AppointmentParkingCountRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

}