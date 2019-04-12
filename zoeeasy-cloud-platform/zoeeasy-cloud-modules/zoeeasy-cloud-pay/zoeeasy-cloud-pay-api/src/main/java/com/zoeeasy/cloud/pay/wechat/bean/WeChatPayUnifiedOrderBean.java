package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *   统一下单请求参数对象
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:25
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayUnifiedOrderBean extends WeChatPayBaseBean {

    /**
     * 商品描述
     */
    @XStreamAlias("body")
    private String body;

    /**
     * 商户订单号(商户系统内部的订单号,32个字符内、可包含字母)
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 总金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 终端IP
     */
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * 通知地址
     */
    @XStreamAlias("notify_url")
    private String notifyURL;

    /**
     * 交易类型
     */
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * 用户标识
     */
    @XStreamAlias("openid")
    private String openId;

}
