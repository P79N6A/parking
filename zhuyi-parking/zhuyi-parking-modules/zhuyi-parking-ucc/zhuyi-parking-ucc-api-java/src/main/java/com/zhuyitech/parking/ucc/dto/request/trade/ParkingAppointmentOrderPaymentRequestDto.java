package com.zhuyitech.parking.ucc.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 预约订单支付下单请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@ApiModel(value = "ParkingAppointmentOrderPaymentRequestDto", description = "预约订单支付下单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingAppointmentOrderPaymentRequestDto extends SessionDto {

    public static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = true)
    @NotNull(message = "订单号不能为空")
    private String orderUuid;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号", required = true)
    @NotNull(message = "预约订单号不能为空")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号", required = true)
    @NotNull(message = "支付订单号不能为空")
    private String payOrderNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     */
    @ApiModelProperty(value = "支付方式(1支付宝, 2微信 3 钱包)", required = true, dataType = "Integer", allowableValues = "1,2,3")
    @NotNull(message = "支付方式不能为空")
    private Integer payWay;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "支付金额(0-100000.00)")
    @Digits(message = "支付金额无效", integer = 100000, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal paymentAmount;

}
