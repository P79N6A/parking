package com.zoeeasy.cloud.pay.trade.dto.request.alipay;


import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 支付宝订单退款请求参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "AlipayTradeRefundQueryRequestDto", description = "支付宝订单退款请求参数")
public class AlipayTradeRefundRequestDto extends SessionDto {

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
     * 需要退款的金额,该金额不能大于订单金额,单位为元，支持两位小数
     */
    @ApiModelProperty(value = "refundAmount", required = true)
    private String refundAmount;

    /**
     * 退款的原因说明
     */
    @ApiModelProperty(value = "refundReason")
    private String refundReason;

    /**
     * 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     */
    @ApiModelProperty(value = "outRequestNo")
    private String outRequestNo;

    /**
     * 商户的操作员编号
     */
    @ApiModelProperty(value = "operatorId")
    private String operatorId;

    /**
     * 商户的门店编号
     */
    @ApiModelProperty(value = "storeId")
    private String storeId;

    /**
     * 	商户的终端编号
     */
    @ApiModelProperty(value = "terminalId")
    private String terminalId;

    /**
     * 	退款包含的商品列表信息
     */
    @ApiModelProperty(value = "goodsDetail")
    private List goodsDetail;
}
