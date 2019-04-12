package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 获取预约订单详情根据ID请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointOrderGetByIdRequestDto", description = "获取预约订单详情根据ID请求参数")
public class ParkingAppointOrderGetByIdRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotEmpty(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;
}
