package com.zoeeasy.cloud.order.inspect.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionPagedRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author AkeemSuper
 * @date 2018/11/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "InspectGetParkingOrderListRequestDto")
public class InspectGetParkingOrderListRequestDto extends SignedSessionPagedRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * parkingId
     */
    @ApiModelProperty(value = "parkingId", required = true)
    @NotNull(message = OrderConstant.PARKING_ID_NOT_EMPTY)
    private Long parkingId;
    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态(0 全部 1 未支付 2 已支付),默认0")
    private Integer payStatus;
}
