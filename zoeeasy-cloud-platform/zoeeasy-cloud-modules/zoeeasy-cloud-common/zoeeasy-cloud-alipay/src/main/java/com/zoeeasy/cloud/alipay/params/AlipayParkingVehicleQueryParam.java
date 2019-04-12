package com.zoeeasy.cloud.alipay.params;

import java.io.Serializable;


/**
 * 支付宝车牌查询参数
 *
 * @author walkman
 */
public class AlipayParkingVehicleQueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 支付宝用户车辆ID，系统唯一。（该参数会在停车平台用户点击查询缴费，跳转到ISV停车缴费查询页面时，从请求中传递）
     */
    private String carId;

    /**
     * accessToken
     */
    private String accessToken;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
