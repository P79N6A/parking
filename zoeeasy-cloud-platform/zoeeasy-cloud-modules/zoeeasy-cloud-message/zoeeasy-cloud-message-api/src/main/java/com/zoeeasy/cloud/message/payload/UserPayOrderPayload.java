package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * userPayOrder
 *
 * @author zwq
 * @date 2018/9/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserPayOrderPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    private String orderNo;

    /**
     * 支付订单号
     */
    private String payOrderNo;

    /**
     * 支付成功时间
     */
    private Date succeedPayTime;

    /**
     * 实际支付金额
     */
    private BigDecimal actualAmount;

}
