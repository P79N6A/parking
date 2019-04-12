package com.zoeeasy.cloud.pay.wechat.params;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * <pre>
 *     申请退款
 * </pre>
 *
 * @author walkman
 * @date 2017-07-11-10:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WeChatRefundParams extends WeChatPayBaseParam {

    /**
     * 微信订单号
     */
    private String transactionId;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 订单金额
     */
    @NotNull
    private Integer totalFee;

    /**
     * 退款金额
     */
    @NotNull
    private Integer refundFee;
}
