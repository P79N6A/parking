package com.zoeeasy.cloud.order.appoint.dto.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import com.zoeeasy.cloud.order.cts.AppointOrderConstant;
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
 * 预约订单确认请求参数
 *
 * @author walkman
 * @date 2018-04-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AppointOrderConfirmRequestDto", description = "预约订单确认请求参数")
public class AppointOrderConfirmRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    @ApiModelProperty(value = "预约订单号", required = true, notes = "预约订单号")
    @NotEmpty(message = AppointOrderConstant.ORDERNO_NOT_EMPTY)
    private String orderNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     * 3  钱包余额
     */
    @ApiModelProperty(value = "支付方式,(1支付宝, 2 微信 3  钱包余额)", required = true, dataType = "Integer", allowableValues = "1,2,3")
    @NotNull(message = AppointOrderConstant.PAYWAY_NOT_EMPTY)
    private Integer payWay;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true, notes = "支付金额(0-100000.00)")
    @Digits(message = "支付金额无效", integer = 6, fraction = 2)
    @NotNull(message = AppointOrderConstant.PAYAMOUNT_NOT_EMPTY)
    @DecimalMin(value = "0", message = AppointOrderConstant.PAYAMOUNT_ILLEGAL)
    @DecimalMax(value = "100000", message = AppointOrderConstant.PAYAMOUNT_ILLEGAL)
    private BigDecimal payAmount;

    /**
     * 车主用户ID
     */
    @ApiModelProperty(value = "车主用户ID", hidden = true)
    @NotNull(message = AppointOrderConstant.CUSTOMER_USERID_NOT_EMPTY)
    private Long customerUserId;
}
