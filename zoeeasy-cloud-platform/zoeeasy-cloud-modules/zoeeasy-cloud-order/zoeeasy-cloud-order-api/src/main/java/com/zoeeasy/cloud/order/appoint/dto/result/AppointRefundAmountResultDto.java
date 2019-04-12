package com.zoeeasy.cloud.order.appoint.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 预约订单退款金额计算计算结果s
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointRefundAmountResultDto", description = "预约订单退款金额计算计算结果s")
public class AppointRefundAmountResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "预定订单号")
    private BigDecimal refundAmount;
}