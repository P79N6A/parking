package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 预约钱包支付
 *
 * @author AkeemSuper
 * @date 2018/9/21 0021
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaySuccessNotificationPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * startTime
     */
    private Date startTime;

    /**
     * parkingName
     */
    private String parkingName;

    /**
     * orderId
     */
    private Long orderId;

    /**
     * orderNo
     */
    private String orderNo;

    /**
     * payedUserId
     */
    private Long payedUserId;

    /**
     * payableAmount
     */
    private BigDecimal payableAmount;

}
