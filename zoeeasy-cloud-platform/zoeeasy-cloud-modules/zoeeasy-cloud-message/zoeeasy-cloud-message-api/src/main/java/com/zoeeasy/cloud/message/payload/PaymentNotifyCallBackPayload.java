package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author Inmier
 * @date 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentNotifyCallBackPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场Code
     */
    private String localCode;

    /**
     * 本地停车场订单流水号
     */
    private String parkingOrderNo;

}
