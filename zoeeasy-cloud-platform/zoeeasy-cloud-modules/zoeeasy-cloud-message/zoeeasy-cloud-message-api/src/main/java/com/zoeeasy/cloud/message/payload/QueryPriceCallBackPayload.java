package com.zoeeasy.cloud.message.payload;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Inmier
 * @date 2019-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class QueryPriceCallBackPayload implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 本地停车场订单流水号
     */
    private String parkingOrderNo;

    /**
     * 车牌号
     */
    private String plateNumber;

    /**
     * 进场时间
     */
    private String passInTime;

    /**
     * 停车时长(分钟)
     */
    private Integer duration;

    /**
     * 应收金额
     */
    private String price;

    /**
     * 免费离场时间
     */
    private Integer freeOutTime;

    /**
     * 结算时间
     */
    private Date costTime;

    /**
     * 云平台订单号
     */
    private String orderNo;
}
