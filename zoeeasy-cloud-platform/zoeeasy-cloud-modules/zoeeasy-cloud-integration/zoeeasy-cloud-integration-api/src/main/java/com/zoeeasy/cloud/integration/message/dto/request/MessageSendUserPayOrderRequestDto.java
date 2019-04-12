package com.zoeeasy.cloud.integration.message.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
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

@ApiModel(value = "MessageSendUserPayOrderRequestDto", description = "停车订单支付完成userPayOrder请求参数")
@Data
public class MessageSendUserPayOrderRequestDto extends BaseDto {

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
