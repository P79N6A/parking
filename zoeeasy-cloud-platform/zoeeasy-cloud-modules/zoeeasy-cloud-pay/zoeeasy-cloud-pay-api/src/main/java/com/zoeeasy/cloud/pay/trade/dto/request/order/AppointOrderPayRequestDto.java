package com.zoeeasy.cloud.pay.trade.dto.request.order;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 预约订单支付请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderPayRequestDto", description = "预约订单支付请求参数")
public class AppointOrderPayRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号", required = true)
    @NotBlank(message = "预约订单号不能为空")
    private String orderNo;

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
    @NotNull(message = "支付金额不能为空")
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    @Digits(message = "支付金额无效", integer = 6, fraction = 2)
    private BigDecimal paymentAmount;

    /**
     * SpbillCreateIp
     */
    @ApiModelProperty(value = "ip", hidden = true, dataType = "String", notes = "请求IP")
    private String spbillCreateIp;

    /**
     * 支付密码
     */
    @ApiModelProperty(value = "支付密码")
    private String tradePassword;
}
