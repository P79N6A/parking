package com.zoeeasy.cloud.pay.trade.dto.request.record;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 根据支付订单号查询支付记录请求参数
 *
 * @Date: 2018/06/27
 * @author: zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PaymentRecordGetByOrderNoRequestDto", description = "根据订单查询")
public class PaymentRecordGetByOrderNoRequestDto extends BaseDto {

    public static final long serialVersionUID = 1L;

    /**
     * 交易订单号
     */
    @NotNull(message = PaymentConst.PAYMENT_PAY_ORDER_NO_NOT_NULL)
    private String orderNo;
}
