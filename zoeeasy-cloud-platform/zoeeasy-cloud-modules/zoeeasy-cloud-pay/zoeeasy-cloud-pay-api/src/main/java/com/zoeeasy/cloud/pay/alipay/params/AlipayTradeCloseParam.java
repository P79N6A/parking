package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付宝订单关闭参数
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AlipayTradeCloseParam extends AlipayPayBaseParam implements Serializable {

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
     * 卖家端自定义的的操作员 ID
     */
    private String operatorId;
}
