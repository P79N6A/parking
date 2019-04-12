package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 根据orderNo获取停车订单请求参数
 *
 * @author zwq
 * @date 2018/10/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderGetByOrderNoRequestDto", description = "根据orderNo获取停车订单请求参数")
public class ParkingOrderGetByOrderNoRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 平台停车账单流水号
     */
    @ApiModelProperty(value = "平台停车账单流水号", required = true)
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;
}
