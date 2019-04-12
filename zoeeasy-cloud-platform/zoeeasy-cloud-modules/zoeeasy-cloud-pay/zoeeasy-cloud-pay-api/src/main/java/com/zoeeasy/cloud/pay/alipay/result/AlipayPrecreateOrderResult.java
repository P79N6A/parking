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
public class AlipayPrecreateOrderResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 必填	64	商户的订单号
     */
    private String outTradeNo;

    /**
     * 必填	1024	当前预下单请求生成的二维码码串，可以用二维码生成工具根据该码串值生成对应的二维码
     */
    private String qrCode;
}
