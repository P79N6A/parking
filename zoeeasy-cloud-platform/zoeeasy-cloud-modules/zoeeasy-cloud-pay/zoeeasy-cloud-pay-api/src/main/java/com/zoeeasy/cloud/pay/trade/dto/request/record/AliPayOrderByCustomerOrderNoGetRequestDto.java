package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取用户支付宝支付记录请求参数
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AliPayOrderByCustomerOrderNoGetRequestDto", description = "获取用户支付宝支付记录请求参数")
public class AliPayOrderByCustomerOrderNoGetRequestDto extends BaseDto {

    public static final long serialVersionUID = 1L;

    /**
     * 商户订单号
     */
    @ApiModelProperty("商户订单号")
    @NotNull(message = PaymentConst.PAYMENT_PAY_USER_NOT_NULL)
    private Long customerId;

    /**
     * 商户订单号
     */
    @ApiModelProperty("商户订单号")
    @NotNull(message = PaymentConst.PAYMENT_PAY_ORDER_NO_NOT_NULL)
    private String outOrderNo;

}
