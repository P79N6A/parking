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
 * 用户停车账单支付下单请求参数
 *
 * @author walkman
 * @date 2018-03-22
 */
@ApiModel(value = "UserParkingOrderPaymentRequestDto", description = "用户停车账单支付下单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserParkingOrderPaymentRequestDto extends SessionDto {

    public static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号", required = true)
    @NotNull(message = "订单号不能为空")
    private String orderUuid;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号", required = true)
    @NotNull(message = "停车订单号不能为空")
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
    @ApiModelProperty(value = "支付方式", required = true, dataType = "Integer", allowableValues = "1,2", notes = "支付方式(支付宝, 微信)")
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
