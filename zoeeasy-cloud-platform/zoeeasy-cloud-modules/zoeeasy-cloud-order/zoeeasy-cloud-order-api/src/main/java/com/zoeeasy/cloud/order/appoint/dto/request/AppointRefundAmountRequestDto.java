package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 预约订单退款金额计算请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRefundAmountRequestDto", description = "预约订单退款金额计算请求参数")
public class AppointRefundAmountRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预定订单号
     */
    @ApiModelProperty(value = "预定订单号", required = true)
    @NotNull(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;
}