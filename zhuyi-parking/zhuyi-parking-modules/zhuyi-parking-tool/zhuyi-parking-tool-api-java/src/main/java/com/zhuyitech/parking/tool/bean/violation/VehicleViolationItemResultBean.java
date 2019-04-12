package com.zhuyitech.parking.tool.bean.violation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zhuyitech.parking.common.constant.Const;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 违章记录
 *
 * @author walkman
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleViolationItemResultBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 违章编码
     */
    private String code;

    /**
     * 违章时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date time;

    /**
     * 罚款金额
     */
    private BigDecimal fine;

    /**
     * 违章地址
     */
    private String address;

    /**
     * 违章处理原因
     */
    private String reason;

    /**
     * 违章扣分
     */
    private Integer point;

    /**
     * 省份
     */
    private String province;

    /**
     * 违章发生城市
     */
    private String violationCity;

    /**
     * 城市
     */
    private String city;

    /**
     * 服务费（与代缴有关，用户可忽略本字段）
     */
    private BigDecimal serviceFee;

    /**
     * 代扣分费用（与代缴有关，用户可忽略本字段）
     */
    private BigDecimal markFee;

    /**
     * 能否勾选办理：0不可勾选, 1可勾选。（与代缴有关，用户可忽略本字段）
     */
    private Integer canSelect;

    /**
     * 违章处理状态：1：未处理，2：处理中，3：已处理，4：不支持（与代缴有关，用户可忽略本字段）
     */
    private Integer processStatus;

    /**
     * 违章官方编码
     */
    private String violationNum;

    /**
     * 执法机构
     */
    private String organName;

    /**
     * 违章缴费状态 不返回表示无法获取该信息，1-未缴费 2-已缴
     */
    private Integer paymentStatus;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getViolationCity() {
        return violationCity;
    }

    public void setViolationCity(String violationCity) {
        this.violationCity = violationCity;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getMarkFee() {
        return markFee;
    }

    public void setMarkFee(BigDecimal markFee) {
        this.markFee = markFee;
    }

    public Integer getCanSelect() {
        return canSelect;
    }

    public void setCanSelect(Integer canSelect) {
        this.canSelect = canSelect;
    }

    public Integer getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus) {
        this.processStatus = processStatus;
    }

    public String getViolationNum() {
        return violationNum;
    }

    public void setViolationNum(String violationNum) {
        this.violationNum = violationNum;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}

