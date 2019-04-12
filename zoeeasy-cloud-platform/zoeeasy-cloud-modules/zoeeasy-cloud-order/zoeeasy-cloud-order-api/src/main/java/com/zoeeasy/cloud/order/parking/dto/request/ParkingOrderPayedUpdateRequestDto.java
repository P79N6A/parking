package com.zoeeasy.cloud.order.parking.dto.request;


import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 停车订单支付信息修改请求参数
 *
 * @author walkman
 * @since 2018/11/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingOrderPayedUpdateRequestDto", description = "停车订单支付信息修改请求参数")
public class ParkingOrderPayedUpdateRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    private Long orderId;
    private String orderNo;
    private Long payedUserId;
    private Date payTime;
    private Integer payStatus;
    private Integer payAmount;
}
