package com.zoeeasy.cloud.pay.trade.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 支付完成请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TradePaymentCompleteRequestDto")
public class TradePaymentCompleteRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_USER_NOT_NULL)
    private Long customerUserId;

    /**
     * 支付订单号
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_ORDER_NO_NOT_NULL)
    private String orderNo;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    private String transactionNo;

    /**
     * 订单来源
     */
    private String orderFrom;

    /**
     * 交易业务类型 ：消费、充值等
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_BIZ_TYPE_NOT_NULL)
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_BIZ_ORDER_NOT_NULL)
    private String bizOrderNo;

    private Integer payWay;

    private Integer payType;

}
