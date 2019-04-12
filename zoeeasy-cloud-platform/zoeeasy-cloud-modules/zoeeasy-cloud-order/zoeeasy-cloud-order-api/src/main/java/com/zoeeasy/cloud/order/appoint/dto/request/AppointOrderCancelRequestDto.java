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
 * 预约订单取消请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderCancelRequestDto", description = "预约订单取消请求参数")
public class AppointOrderCancelRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotBlank(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * customerUserId
     */
    @ApiModelProperty(value = "customerUserId", required = true)
    @NotNull(message = AppointOrderConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;
}
