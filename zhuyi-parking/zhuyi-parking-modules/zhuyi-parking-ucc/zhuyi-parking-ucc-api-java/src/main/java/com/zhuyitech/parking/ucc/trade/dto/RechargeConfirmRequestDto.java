package com.zhuyitech.parking.ucc.trade.dto;

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
 * APP充值确认请求参数
 *
 * @author walkman
 * @date 2018-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RechargeConfirmRequestDto", description = "充值请求参数")
public class RechargeConfirmRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 充值订单号
     */
    @ApiModelProperty(value = "充值订单号", required = true, notes = "充值订单号")
    @NotEmpty(message = "充值订单号")
    private String orderNo;

    /**
     * 充值方式
     * 1  支付宝
     * 2  微信
     */
    @ApiModelProperty(value = "充值方式,(1支付宝, 2 微信)", required = true, dataType = "Integer", allowableValues = "1,2")
    @NotNull(message = "充值方式不能为空")
    private Integer payWay;

    /**
     * 充值金额
     */
    @ApiModelProperty(value = "充值金额", required = true, notes = "充值金额(0-100000.00)")
    @Digits(message = "充值金额无效", integer = 6, fraction = 2)
    @DecimalMin(value = "0", message = "充值金额无效")
    @DecimalMax(value = "100000", message = "充值金额无效")
    private BigDecimal chargeAmount;
}