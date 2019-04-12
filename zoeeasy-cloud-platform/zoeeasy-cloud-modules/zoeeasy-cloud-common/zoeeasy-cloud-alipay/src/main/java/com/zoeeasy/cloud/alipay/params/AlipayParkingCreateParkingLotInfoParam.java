package com.zoeeasy.cloud.alipay.params;

import java.io.Serializable;

/**
 * 录入停车场信息请求参数
 *
 * @author zwq
 */
public class AlipayParkingCreateParkingLotInfoParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ISV停车场ID，由ISV提供，同一个isv或商户范围内唯一
     */
    private String outParkingId;

    /**
     * 停车场地址
     */
    private String parkingAddress;

    /**
     * 停车场类型，1为小区停车场、2为商圈停车场、3为路面停车场、4为园区停车场、5为写字楼停车场、6为私人停车场
     */
    private String parkingLotType;

    /**
     * 高德地图唯一标识
     */
    private String parkingPoiid;

    /**
     * 停车场客服电话
     */
    private String parkingMobile;

    /**
     * 支付方式（1为支付宝在线缴费，2为支付宝代扣缴费，3当面付)，如支持多种方式以','进行间隔
     */
    private String payType;

    /**
     * 商圈id
     */
    private String shopingmallId;

    /**
     * 收费说明
     */
    private String parkingFeeDescription;

    /**
     * 停车场名称
     */
    private String parkingName;

    /**
     * 用户支付未离场的超时时间(以分钟为单位)
     */
    private String timeOut;

    public String getOutParkingId() {
        return outParkingId;
    }

    public void setOutParkingId(String outParkingId) {
        this.outParkingId = outParkingId;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public String getParkingLotType() {
        return parkingLotType;
    }

    public void setParkingLotType(String parkingLotType) {
        this.parkingLotType = parkingLotType;
    }

    public String getParkingPoiid() {
        return parkingPoiid;
    }

    public void setParkingPoiid(String parkingPoiid) {
        this.parkingPoiid = parkingPoiid;
    }

    public String getParkingMobile() {
        return parkingMobile;
    }

    public void setParkingMobile(String parkingMobile) {
        this.parkingMobile = parkingMobile;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getShopingmallId() {
        return shopingmallId;
    }

    public void setShopingmallId(String shopingmallId) {
        this.shopingmallId = shopingmallId;
    }

    public String getParkingFeeDescription() {
        return parkingFeeDescription;
    }

    public void setParkingFeeDescription(String parkingFeeDescription) {
        this.parkingFeeDescription = parkingFeeDescription;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }
}
