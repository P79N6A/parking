package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付宝关闭订单结果
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayTradeCloseResult implements Serializable {

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
}
