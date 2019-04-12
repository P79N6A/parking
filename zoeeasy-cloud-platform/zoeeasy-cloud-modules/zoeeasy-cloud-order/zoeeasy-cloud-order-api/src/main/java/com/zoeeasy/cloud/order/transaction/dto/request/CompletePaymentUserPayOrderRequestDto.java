package com.zoeeasy.cloud.order.transaction.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车订单支付完成userPayOrder请求参数
 *
 * @author zwq
 * @date 2018-08-08
 */
@Data
@ApiModel(value = "CompletePaymentUserPayOrderRequestDto", description = "停车订单支付完成userPayOrder请求参数")
public class CompletePaymentUserPayOrderRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    private String payOrderNo;

    /**
     * 支付成功时间
     */
    @ApiModelProperty(value = "支付成功时间")
    private Date succeedPayTime;

    /**
     * 实际支付金额
     */
    @ApiModelProperty(value = "实际支付金额")
    private BigDecimal actualAmount;
}
