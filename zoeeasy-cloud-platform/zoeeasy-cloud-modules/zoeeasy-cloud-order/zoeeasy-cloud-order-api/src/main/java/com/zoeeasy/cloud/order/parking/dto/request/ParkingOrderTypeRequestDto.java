package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Date: 2018/6/29
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderTypeRequestDto", description = "订单类型列表")
public class ParkingOrderTypeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;
}
