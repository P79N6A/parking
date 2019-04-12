package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description 停车账单查对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingPaymentInfoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 账单编号
     */
    private String billCode;

    /**
     * 停车场编号
     */
    private String parkCode;

    /**
     * 停车场名称
     */
    private String parkName;

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 车牌颜色
     */
    private Integer plateColor;

    /**
     * 车辆进场时间
     */
    private Long enterTime;
    /**
     * 结算时间
     */
    private Long costTime;

    /**
     * 停车时长
     */
    private Integer parkPeriodTime;

    /**
     * 总收费金额
     */
    private Integer totalCost;

}
