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
 * 用户支付下单请求参数
 */
@ApiModel(value = "UserPlaceRechargeOrderRequestDto", description = "用户支付下单请求参数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPlaceRechargeOrderRequestDto extends SessionDto {

    public static final long serialVersionUID = 1L;

    /**
     * 充值订单号
     */
    @ApiModelProperty(value = "充值订单号", required = true)
    @NotNull(message = "充值订单号不能为空")
    private String orderUuid;

    /**
     * 充值订单号
     */
    @ApiModelProperty(value = "充值订单号", required = true)
    @NotNull(message = "充值订单号不能为空")
    private String rechargeOrderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号", required = true)
    @NotNull(message = "支付订单号不能为空")
    private String payOrderNo;

    /**
     * 充值方式
     * 1  支付宝
     * 2  微信
     */
    @ApiModelProperty(value = "充值方式", required = true, dataType = "Integer", allowableValues = "1,2", notes = "充值方式(支付宝, 微信)")
    @NotNull(message = "充值方式不能为空")
    private Integer payWay;

    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额", required = true, notes = "充值金额(0-10000.00)")
    @Digits(message = "充值金额无效", integer = 100000, fraction = 2)
    @DecimalMin(value = "0", message = "支付金额无效")
    @DecimalMax(value = "100000", message = "支付金额无效")
    private BigDecimal chargeAmount;

}
