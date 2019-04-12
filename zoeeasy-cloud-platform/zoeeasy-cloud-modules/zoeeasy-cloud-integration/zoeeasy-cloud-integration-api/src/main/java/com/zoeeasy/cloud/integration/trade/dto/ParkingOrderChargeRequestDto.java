package com.zoeeasy.cloud.integration.trade.dto;

import com.scapegoat.infrastructure.core.dto.request.sign.SignedSessionRequestDto;
import com.zoeeasy.cloud.order.cts.OrderConstant;
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
 * 停车账单收款请求参数
 *
 * @author walkman
 * @date 2018-01-11
 */
@ApiModel(value = "ParkingOrderChargeRequestDto", description = "停车账单支付请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderChargeRequestDto extends SignedSessionRequestDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车账单号
     */
    @ApiModelProperty(value = "停车账单号", required = true)
    @NotEmpty(message = OrderConstant.ORDER_NO_NOT_EMPTY)
    private String orderNo;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotNull(message = OrderConstant.PLATE_NUMBER_NOT_EMPTY)
    private String plateNumber;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     */
    @ApiModelProperty(value = "支付方式,(1支付宝, 2 微信)", required = true, dataType = "Integer", allowableValues = "1,2")
    @NotNull(message = "支付方式不能为空")
    private Integer payWay;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型,(11 支付宝扫码 23　微信扫码))", dataType = "Integer", allowableValues = "11,23")
    private Integer payType;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "金额(0-10000.00)")
    @Digits(message = "支付金额无效", integer = 100000, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal paymentAmount;

    /**
     * SpbillCreateIp
     */
    @ApiModelProperty(value = "ip", hidden = true, dataType = "String", notes = "请求IP")
    private String spbillCreateIp;

}
