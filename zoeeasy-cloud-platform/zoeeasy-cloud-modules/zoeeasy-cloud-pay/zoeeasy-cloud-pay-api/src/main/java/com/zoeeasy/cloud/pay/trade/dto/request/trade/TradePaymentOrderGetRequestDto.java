package com.zoeeasy.cloud.pay.trade.dto.request.trade;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zoeeasy.cloud.pay.constant.PaymentConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 根据业务类型及业务订单号获取支付订单请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TradePaymentOrderGetRequestDto")
public class TradePaymentOrderGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @NotNull(message = PaymentConst.PAYMENT_PAY_USER_NOT_NULL)
    private Long customerUserId;

    @ApiModelProperty(value = "业务类型")
    @NotNull(message = PaymentConst.PAYMENT_PAY_BIZ_TYPE_NOT_NULL)
    private Integer bizOrderType;

    /**
     * 交易订单号
     */
    @ApiModelProperty(value = "交易订单号")
    @NotBlank(message = PaymentConst.PAYMENT_PAY_BIZ_ORDER_NOT_NULL)
    private String bizOrderNo;
}
