package com.zoeeasy.cloud.pay.trade.dto.request.order;

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
 * 停车账单支付请求参数
 *
 * @author walkman
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ParkingRecordPaymentRequestDto", description = "停车账单支付请求参数")
public class ParkingRecordPaymentRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车记录流水
     */
    @ApiModelProperty(value = "停车记录流水", required = true)
    @NotEmpty(message = "记录流水不能为空")
    private String recordNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     * 3  钱包余额
     */
    @ApiModelProperty(value = "支付方式,(1支付宝, 2 微信 3  钱包余额)", required = true, dataType = "Integer", allowableValues = "1,2,3")
    @NotNull(message = "支付方式不能为空")
    private Integer payWay;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "金额(0-10000.00)")
    @Digits(message = "支付金额无效", integer = 6, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal paymentAmount;

    /**
     * SpbillCreateIp
     */
    @ApiModelProperty(value = "ip", hidden = true, dataType = "String", notes = "请求IP")
    private String spbillCreateIp;

    /**
     * 微信openId
     */
    @ApiModelProperty(value = "微信openId", required = true, notes = "微信openId")
    private String openId;
}