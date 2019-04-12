package com.zoeeasy.cloud.pay.alipay.params;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付宝请求参数基类
 *
 * @author walkman
 */
@Data
public class AlipayPayBaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝APPID
     */
    private String appId;

    /**
     * 支付宝sellerId
     */
    private String sellerId;

    /**
     * 支付宝sellerId
     */
    private String privateKey;

    private String publicKey;
}
