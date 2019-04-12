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
 * 停车账单支付请求参数
 *
 * @author walkman
 * @date 2018-01-11
 */
@ApiModel(value = "ParkingOrderPaymentRequestDto", description = "停车账单支付请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class ParkingOrderPaymentRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 停车账单号
     */
    @ApiModelProperty(value = "停车账单号", required = true)
    @NotEmpty(message = "停车账单号不能为空")
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
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型,(APP(app支付),NATIVE(扫码支付), JSAPI(公众号支付))", dataType = "String", allowableValues = "APP,NATIVE,JSAPI")
    private String payType;

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

    /**
     * openId
     */
    @ApiModelProperty(value = "openId", required = true, notes = "微信openId")
    private String openId;

    /**
     * 支付密码
     */
    @ApiModelProperty(value = "支付密码")
    private String tradePassword;

}
