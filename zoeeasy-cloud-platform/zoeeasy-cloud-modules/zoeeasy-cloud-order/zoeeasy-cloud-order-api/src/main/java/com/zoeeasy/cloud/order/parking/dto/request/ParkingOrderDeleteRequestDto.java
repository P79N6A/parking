package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 删除停车账单请求参数
 *
 * @Date: 2018/7/25
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderDeleteRequestDto")
public class ParkingOrderDeleteRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @NotBlank(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotBlank(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;
}
