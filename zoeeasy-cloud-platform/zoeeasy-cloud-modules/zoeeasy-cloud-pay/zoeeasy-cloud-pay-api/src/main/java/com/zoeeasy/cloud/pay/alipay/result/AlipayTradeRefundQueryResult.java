package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 支付宝退款查询结果
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayTradeRefundQueryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 网关返回码
     */
    private String code;

    /**
     * 网关返回码描述
     */
    private String msg;

    /**
     * 业务返回码
     */
    private String subCode;

    /**
     * 业务返回码描述
     */
    private String subMsg;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 商家订单号
     */
    private String outTradeNo;

    /**
     * 本笔退款对应的退款请求号
     */
    private String outRequestNo;

    /**
     * 发起退款时，传入的退款原因
     */
    private String refundReason;

    /**
     * 该笔退款所对应的交易的订单金额
     */
    private BigDecimal totalAmount;

    /**
     * 本次退款请求，对应的退款金额
     */
    private BigDecimal refundAmount;
}
