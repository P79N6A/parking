package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 第三方支付下单消息
 *
 * @author walkman
 * @date 2018/11/27 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TradePaymentOrderPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long customerId;

    /**
     * 支付订单号
     */
    private String orderNo;

    /**
     * 业务类型
     */
    private Integer bizType;

    /**
     *
     */
    private String bizOrderNo;

    private Integer payWay;

    private Integer payType;

    private Integer orderAmount;

}
