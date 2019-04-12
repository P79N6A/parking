package com.zoeeasy.cloud.pay.trade.dto.result.alipay;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支付宝订单退款查询响应参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeRefundQueryResponseDto", description = "支付宝订单退款查询响应参数")
public class AlipayTradeRefundQueryResponseDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    @ApiModelProperty(value = "code", required = true)
    private String code;

    /**
     * 网关返回码描述
     */
    @ApiModelProperty(value = "msg", required = true)
    private String msg;

    /**
     * 业务返回码
     */
    @ApiModelProperty(value = "subCode")
    private String subCode;

    /**
     * 业务返回码描述
     */
    @ApiModelProperty(value = "subMsg")
    private String subMsg;

    /**
     * 支付宝交易号
     */
    @ApiModelProperty(value = "tradeNo", required = true)
    private String tradeNo;

    /**
     * 商家订单号
     */
    @ApiModelProperty(value = "outTradeNo", required = true)
    private String outTradeNo;

    /**
     * 本笔退款对应的退款请求号
     */
    @ApiModelProperty(value = "outRequestNo", required = true)
    private String outRequestNo;

    /**
     * 发起退款时，传入的退款原因
     */
    @ApiModelProperty(value = "refundReason", required = true)
    private String refundReason;

    /**
     * 该笔退款所对应的交易的订单金额
     */
    @ApiModelProperty(value = "totalAmount", required = true)
    private String totalAmount;

    /**
     * 本次退款请求，对应的退款金额
     */
    @ApiModelProperty(value = "refundAmount", required = true)
    private String refundAmount;
}
