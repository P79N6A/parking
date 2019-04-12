package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 获取预约订单详情根据用户ID请求参数
 *
 * @author zwq
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointOrderGetByUserIdRequestDto", description = "获取预约订单详情根据用户ID请求参数")
public class ParkingAppointOrderGetByCustomerRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = AppointOrderConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;

    /**
     * parkingId
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotNull(message = AppointOrderConstant.PARKINGID_NOT_EMPTY)
    private Long parkingId;

    /**
     * plateNumber
     */
    @ApiModelProperty(value = "plateNumber", required = true)
    @NotBlank(message = AppointOrderConstant.PLATENUMBER_NOT_EMPTY)
    private String plateNumber;
}
