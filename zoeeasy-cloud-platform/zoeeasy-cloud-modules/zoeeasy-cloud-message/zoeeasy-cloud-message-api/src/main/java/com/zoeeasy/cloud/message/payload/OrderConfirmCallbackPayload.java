package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 账单支付回调消息
 *
 * @author AkeemSuper
 * @date 2018/12/6 0006
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderConfirmCallbackPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方平台账单编号
     */
    private String thirdBillNo;

    /**
     * 平台停车场ID
     */
    private Long parkingId;

    /**
     * 实付金额
     */
    private Integer actualPayAmount;

    /**
     * 支付方式
     */
    private Integer payWay;

    /**
     * 支付成功时间
     */
    private Date succeedPayTime;

}
