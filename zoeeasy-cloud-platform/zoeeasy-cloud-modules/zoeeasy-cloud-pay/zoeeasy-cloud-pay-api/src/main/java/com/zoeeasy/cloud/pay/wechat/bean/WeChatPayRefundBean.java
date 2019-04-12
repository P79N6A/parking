package com.zoeeasy.cloud.pay.wechat.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 *     申请退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:53
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WeChatPayRefundBean extends WeChatPayBaseBean {

    /**
     * 微信订单号
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 订单金额
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * 退款金额
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

}
