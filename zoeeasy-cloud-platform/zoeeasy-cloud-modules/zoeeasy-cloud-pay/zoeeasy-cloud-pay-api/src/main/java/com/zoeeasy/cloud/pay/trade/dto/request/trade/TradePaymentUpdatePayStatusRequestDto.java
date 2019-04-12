package com.zoeeasy.cloud.pay.trade.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * 通过用户ID、支付订单号更新支付订单状态请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TradePaymentUpdatePayStatusRequestDto")
public class TradePaymentUpdatePayStatusRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(message = PaymentConst.PAYMENT_PAY_USER_NOT_NULL)
    private Long customerUserId;

    @NotNull(message = PaymentConst.PAYMENT_PAY_ORDER_NO_NOT_NULL)
    private String orderNo;

    private String transactionNo;

    private Date successPayTime;

    private Integer payStatus;

}