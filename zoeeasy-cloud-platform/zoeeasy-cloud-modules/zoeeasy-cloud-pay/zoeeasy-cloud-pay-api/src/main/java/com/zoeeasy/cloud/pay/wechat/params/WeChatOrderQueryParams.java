package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *   订单查询请求对象
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatOrderQueryParams extends WeChatPayBaseParam {

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 随机字符串
     */
    private String nonceStr;
}
