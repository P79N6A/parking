package com.zoeeasy.cloud.alipay.params;


import java.io.Serializable;

/**
 * 支付宝订单同步接口
 *
 * @author zwq
 */
public class AlipayParkingSyncOrderParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌
     */
    private String carNumber;
    /**
     * 如果是停车卡缴费，则填入停车卡卡号，否则为'*'
     */
    private String cardNumber;
    /**
     * 停车时长（以分为单位）
     */
    private String inDuration;
    /**
     * 入场时间，格式"YYYY-MM-DD HH:mm:ss"，24小时制
     */
    private String inTime;
    /**
     * 支付宝支付流水，系统唯一
     */
    private String orderNo;
    /**
     * 设备商订单状态，0：成功，1：失败
     */
    private String orderStatus;
    /**
     * 订单创建时间，格式"YYYY-MM-DD HH:mm:ss"，24小时制
     */
    private String orderTime;
    /**
     * 设备商订单号，由ISV系统生成
     */
    private String outOrderNo;
    /**
     * ISV停车场ID，由ISV提供，同一个isv或商户范围内唯一
     */
    private String outParkingId;
    /**
     * 支付宝停车场id，系统唯一
     */
    private String parkingId;
    /**
     * 停车场名称，由ISV定义，尽量与高德地图上的一致
     */
    private String parkingName;
    /**
     * 缴费金额，保留小数点后两位
     */
    private String payMoney;
    /**
     * 缴费时间, 格式"YYYYMM-DD HH:mm:ss"，24小时制
     */
    private String payTime;
    /**
     * 付款方式，1：支付宝在线缴费 ，2：支付宝代扣缴费
     */
    private String payType;
    /**
     * 停车缴费支付宝用户的ID，请ISV保证用户ID的正确性，以免导致用户在停车平台查询不到相关的订单信息
     */
    private String userId;

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getInDuration() {
        return inDuration;
    }

    public void setInDuration(String inDuration) {
        this.inDuration = inDuration;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOutParkingId() {
        return outParkingId;
    }

    public void setOutParkingId(String outParkingId) {
        this.outParkingId = outParkingId;
    }

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
