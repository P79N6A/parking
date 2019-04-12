package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约钱包支付
 *
 * @author zwq
 * @date 2018/9/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RechargeSuccessPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long customerUserId;

    /**
     * 预约订单号
     */
    private String orderNo;

    /**
     * 支付方式
     * 1  支付宝
     * 2  微信
     * 3  钱包余额
     */
    private Integer payWay;

    /**
     * 支付业务订单号,对应支付宝tradeNo或微信支付outTradeNo
     */
    private String transactionNo;

    /**
     * 交易业务类型 ：消费、充值等
     */
    private Integer bizOrderType;

    /**
     * 交易业务订单号
     */
    private String bizOrderNo;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 支付成功时间
     */
    private Date succeedPayTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 实际支付金额
     */
    private BigDecimal totalAmount;
}
