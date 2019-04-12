package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 根据车牌号订单号获取停车订单请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderGetPlateOrderNoRequestDto", description = "根据车牌号订单号获取停车订单请求参数")
public class ParkingOrderGetPlateOrderNoRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号", required = true)
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @NotEmpty(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;
}