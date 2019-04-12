package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 支付宝订单退款参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayTradeRefundParam extends AlipayPayBaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商家订单号
     */
    private String outTradeNo;

    /**
     * 支付宝交易号
     */
    private String tradeNo;

    /**
     * 需要退款的金额,该金额不能大于订单金额,单位为元，支持两位小数
     */
    private String refundAmount;

    /**
     * 退款的原因说明
     */
    private String refundReason;

    /**
     * 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传。
     */
    private String outRequestNo;

    /**
     * 商户的操作员编号
     */
    private String operatorId;

    /**
     * 商户的门店编号
     */
    private String storeId;

    /**
     * 商户的终端编号
     */
    private String terminalId;

    /**
     * 退款包含的商品列表信息
     */
    private List goodsDetail;
}
