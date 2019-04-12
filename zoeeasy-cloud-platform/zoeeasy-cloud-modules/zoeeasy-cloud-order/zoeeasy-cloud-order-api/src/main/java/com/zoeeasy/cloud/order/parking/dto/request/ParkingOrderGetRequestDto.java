package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 停车订单详情请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderGetRequestDto")
public class ParkingOrderGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @NotBlank(message = "订单号不能为空")
    private String orderNo;
}
