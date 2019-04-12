package com.zoeeasy.cloud.hikvision.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @Description 停车缴费记录对象
 * @Date: 2018/1/13 0013
 * @author: AkeemSuper
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TempCarChargeRecordsBean implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 停车场编号
     */
    private String parkCode;

    /**
     * 停车场名称
     */
    private String parkName;

    /**
     * 车牌号码
     */
    private String plateNo;

    /**
     * 车牌颜色
     */
    private Integer plateColor;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 应收金额
     */
    private String needPay;
    /**
     * 实收金额
     */
    private String payMoney;
    /**
     * 支付类型
     */
    private Integer payType;
    /**
     * 车辆进场时间
     */
    private Long inTime;
    /**
     * 缴费时间
     */
    private Long payTime;
    /**
     * 操作员
     */
    private String collectorName;

    public String getParkCode() {
        return parkCode;
    }

    public void setParkCode(String parkCode) {
        this.parkCode = parkCode;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getNeedPay() {
        return needPay;
    }

    public void setNeedPay(String needPay) {
        this.needPay = needPay;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getInTime() {
        return inTime;
    }

    public void setInTime(Long inTime) {
        this.inTime = inTime;
    }

    public Long getPayTime() {
        return payTime;
    }

    public void setPayTime(Long payTime) {
        this.payTime = payTime;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    @Override
    public String toString() {
        return "TempCarChargeRecordsBean{" +
                "parkCode='" + parkCode + '\'' +
                ", parkName='" + parkName + '\'' +
                ", plateNo='" + plateNo + '\'' +
                ", plateColor=" + plateColor +
                ", cardNo='" + cardNo + '\'' +
                ", needPay='" + needPay + '\'' +
                ", payMoney='" + payMoney + '\'' +
                ", payType=" + payType +
                ", inTime=" + inTime +
                ", payTime=" + payTime +
                ", collectorName='" + collectorName + '\'' +
                '}';
    }
}
