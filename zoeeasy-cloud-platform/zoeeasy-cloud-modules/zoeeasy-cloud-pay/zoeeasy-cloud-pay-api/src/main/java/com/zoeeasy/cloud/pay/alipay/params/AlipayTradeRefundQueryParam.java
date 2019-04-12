package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付宝退款查询参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayTradeRefundQueryParam extends AlipayPayBaseParam implements Serializable {

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
     * 请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号
     */
    private String outRequestNo;
}
