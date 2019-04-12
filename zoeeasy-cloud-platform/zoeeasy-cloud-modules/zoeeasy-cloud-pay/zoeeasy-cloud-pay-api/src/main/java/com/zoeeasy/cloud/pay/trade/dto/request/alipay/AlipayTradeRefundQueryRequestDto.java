package com.zoeeasy.cloud.pay.trade.dto.request.alipay;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝订单退款查询请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeRefundQueryRequestDto", description = "支付宝订单退款查询请求参数")
public class AlipayTradeRefundQueryRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 商家订单号
     */
    @ApiModelProperty(value = "outTradeNo")
    private String outTradeNo;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty(value = "tradeNo")
    private String tradeNo;

    /**
     * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     */
    @ApiModelProperty(value = "outRequestNo", required = true)
    private String outRequestNo;
}
