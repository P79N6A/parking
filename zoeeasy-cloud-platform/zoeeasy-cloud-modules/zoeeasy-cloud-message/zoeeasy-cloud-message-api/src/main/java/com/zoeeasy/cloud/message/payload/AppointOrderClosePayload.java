package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author zwq
 * @date 2018/11/27 0025
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AppointOrderClosePayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预约订单号
     */
    private String orderNo;
}
