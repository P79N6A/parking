package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 预约订单支付判断
 *
 * @author zwq
 * @date 2018-08-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointOrderPayCheckRequestDto", description = "预约订单支付判断")
public class ParkingAppointOrderPayCheckRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预定订单号
     */
    @ApiModelProperty(value = "预定订单号", required = true)
    @NotBlank(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = AppointOrderConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;
}
