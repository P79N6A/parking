package com.zoeeasy.cloud.pay.trade.dto.request.alipay;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝订单查询请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeQueryRequestDto", description = "支付宝订单查询请求参数")
public class AlipayTradeQueryRequestDto extends SessionDto {

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
}
