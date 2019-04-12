package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description: 预约车位
 * @author: AkeemSuper
 * @date: 2018/3/12 0012
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingReservationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单编号
     */
    private String billNo;

    /**
     * 车位号
     */
    private String plotNum;

}
