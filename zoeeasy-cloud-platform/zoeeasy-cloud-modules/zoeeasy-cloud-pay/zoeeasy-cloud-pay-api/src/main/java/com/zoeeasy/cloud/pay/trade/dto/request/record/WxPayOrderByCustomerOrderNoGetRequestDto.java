package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取微信支付记录请求参数
 *
 * @Date: 2018/09/12
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "WeiXinRecordGetByOutOrderNoGetRequestDto", description = "获取微信支付记录请求参数")
public class WxPayOrderByCustomerOrderNoGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
