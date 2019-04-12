package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedRequestDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取平台预约订单请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingAppointOrderGetRequestDto", description = "获取预约订单详情请求参数")
public class ParkingAppointOrderGetRequestDto extends SignedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车主用户ID
     */
    @ApiModelProperty(value = "车主用户ID")
    @NotNull(message = AppointOrderConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;

    /**
     * 预定订单号
     */
    @ApiModelProperty(value = "预定订单号", required = true)
    @NotNull(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

}
