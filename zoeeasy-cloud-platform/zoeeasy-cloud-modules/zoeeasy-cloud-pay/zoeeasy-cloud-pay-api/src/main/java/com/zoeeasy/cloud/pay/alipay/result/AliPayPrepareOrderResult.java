package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 支付宝下单结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AliPayPrepareOrderResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderInfo;

    /**
     * app支付接口2.0
     */
    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

    /**
     * 该交易在支付宝系统中的交易流水号
     */
    private String tradeNo;

    /**
     * 笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。
     */
    private String totalAmount;

    /**
     * 收款支付宝账号对应的支付宝唯一用户号
     */
    private String sellerId;

}