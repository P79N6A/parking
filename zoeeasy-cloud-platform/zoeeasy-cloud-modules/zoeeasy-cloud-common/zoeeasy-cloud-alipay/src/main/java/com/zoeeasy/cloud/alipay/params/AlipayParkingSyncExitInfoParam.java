package com.zoeeasy.cloud.alipay.params;


import java.io.Serializable;

/**
 * 支付宝订单查询参数
 *
 * @author zwq
 */
public class AlipayParkingSyncExitInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝返回停车场id，系统唯一
     */
    private String parkingId;

    /**
     * 车牌号
     */
    private String carNumber;

    /**
     * 车辆入场的时间，格式"YYYY-MM-DD HH:mm:ss"，24小时制
     */
    private String outTime ;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
