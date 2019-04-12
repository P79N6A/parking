package com.zoeeasy.cloud.alipay.result;

import java.io.Serializable;

/**
 * 车牌查询结果
 *
 * @author zwq
 */
public class AlipayParkingVehicleQueryResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌信息（utf-8编码）
     */
    private String carNumber;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

}
