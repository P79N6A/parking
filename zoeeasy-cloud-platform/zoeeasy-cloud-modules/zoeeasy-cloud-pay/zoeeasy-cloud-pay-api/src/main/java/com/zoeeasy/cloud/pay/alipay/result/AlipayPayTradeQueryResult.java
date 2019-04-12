package com.zoeeasy.cloud.pay.alipay.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 支付宝订单查询结果
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AlipayPayTradeQueryResult implements Serializable {

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
     * 用户支付宝号
     */
    private String buyerLogonId;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 交易的订单金额
     */
    private String totalAmount;

    /**
     * 实收金额
     */
    private String receiptAmount;

    /**
     * 买家实付金额
     */
    private String buyerPayAmount;

    /**
     * 积分支付的金额
     */
    private String pointAmount;

    /**
     * 交易中用户支付的可开具发票的金额
     */
    private String invoiceAmount;

    /**
     * 本次交易打款给卖家的时间
     */
    private Date sendPayDate;

    /**
     * 商户门店编号
     */
    private String storeId;

    /**
     * 商户机具终端编号
     */
    private String terminalId;

    /**
     * 请求交易支付中的商户店铺的名称
     */
    private String storeName;

    /**
     * 买家在支付宝的用户id
     */
    private String buyerUserId;

    /**
     * 买家用户类型
     */
    private String buyerUserType;

    /**
     * 交易支付使用的资金渠道
     */
    private List fundBillList;
}
