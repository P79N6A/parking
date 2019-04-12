package com.zoeeasy.cloud.alipay.constant;


/**
 * @Description alipay 中的配置
 * @Date: 2018/1/12 0012
 * @author: zwq
 */
public class AlipayConstants {
    
    private AlipayConstants() {
    }

    /**
     * 字符编码格式
     */
    public static final String CHARSET = "UTF-8";

    /**
     *
     */
    public static final String FORMAT = "JSON";

    /**
     * 支付宝网关
     */
    public static final String ALIPAY_GATEWAY_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 签名方式
     */
    public static final String SIGN_TYPE = "RSA2";

}
