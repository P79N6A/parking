package com.zhuyitech.parking.integration.order.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 停车账单支付确认结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@ApiModel(value = "PaymentConfirmResultDto", description = "停车账单支付确认结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentConfirmResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 支付订单号
     */
    @ApiModelProperty("支付订单号")
    private String orderNo;

    /**
     * 支付金额
     */
    @ApiModelProperty("支付金额")
    private BigDecimal totalAmount;

    /**
     * 是否支付成功
     */
    @ApiModelProperty("是否支付成功")
    private Boolean succeed;

    /**
     * 支付成功时间
     */
    @ApiModelProperty("支付成功时间")
    private Date succeedTime;

}
