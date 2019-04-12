package com.zhuyitech.parking.integration.order.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 停车账单支付确认结果
 *
 * @author walkman
 * @date 2018-01-11
 */
@ApiModel(value = "ParkingOrderPaymentConfirmRequestDto", description = "停车账单支付确认结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderPaymentConfirmRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 订单流水号
     */
    @ApiModelProperty("订单流水号")
    @NotEmpty(message = "订单流水号不能为空")
    private String orderNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     */
    @ApiModelProperty(value = "支付方式(1支付宝, 2微信 3钱包)", required = true, dataType = "Integer", allowableValues = "1,2,3")
    @NotNull(message = "支付方式不能为空")
    private Integer payWay;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "支付金额(0-100000.00)")
    @Digits(message = "支付金额无效", integer = 100000, fraction = 2)
    @NotNull(message = "支付金额不能为空")
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal payAmount;

}
