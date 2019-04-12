package com.zoeeasy.cloud.alipay.result;


import java.io.Serializable;


/**
 * 支付宝录入停车场信息结果
 *
 * @author walkman
 */
public class AlipayParkingCreateParkingLotInfoResult implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝返回停车场id。成功不为空，失败返回空
     */
    private String parkingId;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }
}
