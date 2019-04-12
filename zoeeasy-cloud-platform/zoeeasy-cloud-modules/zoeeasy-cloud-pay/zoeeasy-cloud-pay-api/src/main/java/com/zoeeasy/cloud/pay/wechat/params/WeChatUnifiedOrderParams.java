package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 *   统一下单请求参数对象
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatUnifiedOrderParams extends WeChatPayBaseParam {

    /**
     * 商品描述
     */
    @NotNull
    private String body;

    /**
     * 商户订单号(商户系统内部的订单号,32个字符内、可包含字母)
     */
    @NotNull
    private String outTradeNo;

    /**
     * 总金额
     */
    @NotNull
    private Integer totalFee;

    /**
     * 终端IP
     */
    @NotNull
    private String spbillCreateIp;

    /**
     * 通知地址
     */
    @NotNull
    private String notifyURL;

    /**
     * 交易类型
     */
    @NotNull
    private String tradeType;

    /**
     * 用户标识
     */
    @NotNull
    private String openId;

    /**
     * 随机字符串
     */
    @NotNull
    private String nonceStr;

}
