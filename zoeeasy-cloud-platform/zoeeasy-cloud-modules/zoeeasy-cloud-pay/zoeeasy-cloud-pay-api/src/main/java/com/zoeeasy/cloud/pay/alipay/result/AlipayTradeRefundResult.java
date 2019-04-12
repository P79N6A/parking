package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 支付宝退款结果
 *
 * @author zwq
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayTradeRefundResult implements Serializable {

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

    /**
     * 用户的登录id
     */
    private String buyerLogonId;

    /**
     * 本次退款是否发生了资金变化
     */
    private String fundChange;

    /**
     * 退款总金额
     */
    private String refundFee;

    /**
     * 退款支付时间
     */
    private Date gmtRefundPay;

    /**
     * 退款使用的资金渠道
     */
    private List refundDetailItemList;

    /**
     * 交易在支付时候的门店名称
     */
    private String storeName;

    /**
     * 买家在支付宝的用户id
     */
    private String buyerUserId;

    /**
     * 本次退款金额中买家退款金额
     */
    private String presentRefundBuyerAmount;

    /**
     * 本次退款金额中平台优惠退款金额
     */
    private String presentRefundDiscountAmount;

    /**
     * 本次退款金额中商家优惠退款金额
     */
    private String presentRefundMdiscountAmount;
}
