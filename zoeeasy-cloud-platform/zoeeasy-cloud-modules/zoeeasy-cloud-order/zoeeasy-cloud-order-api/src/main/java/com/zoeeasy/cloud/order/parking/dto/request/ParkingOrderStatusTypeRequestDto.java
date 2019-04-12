package com.zoeeasy.cloud.order.parking.dto.request;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 订单状态列表
 *
 * @Date: 2018/6/29
 * @author: wh
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderStatusTypeRequestDto", description = "订单状态列表")
public class ParkingOrderStatusTypeRequestDto extends SignedSessionRequestDto {
    private static final long serialVersionUID = 1L;
}
