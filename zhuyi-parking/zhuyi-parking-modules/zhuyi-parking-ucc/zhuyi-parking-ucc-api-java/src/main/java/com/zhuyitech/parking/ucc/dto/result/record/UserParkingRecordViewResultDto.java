package com.zhuyitech.parking.ucc.dto.result.record;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户停车记录结果视图模型
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@ApiModel(value = "UserParkingRecordViewResultDto", description = "用户停车记录结果视图模型")
public class UserParkingRecordViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 记录流水号
     */
    @ApiModelProperty(value = "记录流水号")
    private String recordNo;

    /**
     * 停车场名称
     */
    @ApiModelProperty(value = "停车场名称")
    private String parkingName;

    /**
     * 车位编号
     */
    @ApiModelProperty(value = "车位编号")
    private String parkingLotCode;

    /**
     * 停车日期
     */
    @ApiModelProperty(value = "停车日期")
    private String parkingTime;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    private String startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    private String endTime;

    /**
     * 停车时间(分钟)
     */
    @ApiModelProperty(value = "停车时间")
    private BigDecimal parkingDuration;

    /**
     * 停车时间(XX天XX小时XX分XX秒)
     */
    @ApiModelProperty(value = "停车时间")
    private String parkingLength;

    /**
     * 停车场收费标准
     */
    @ApiModelProperty(value = "收费标准")
    private String chargeDescription;

    /**
     * 是否可以支付
     */
    @ApiModelProperty(value = "收费标准")
    private Boolean payable;

    /**
     * 是否需要支付
     */
    @ApiModelProperty(value = "是否需要支付")
    private Boolean needPay;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private String payableAmount;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private String actualPayAmount;

    /**
     * 停车状态
     */
    @ApiModelProperty(value = "停车状态")
    private Integer status;

    /**
     * 支付状态
     */
    @ApiModelProperty(value = "支付状态", notes = "0:未支付  1:已支付")
    private Integer payStatus;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    private String payTime;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号")
    @SensitiveInfo(value = SensitiveType.PLATE_NUMBER)
    private String plateNumber;

    /**
     * 车牌颜色
     * LicensePlateColorEnum
     */
    @ApiModelProperty(value = "车牌颜色")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer plateColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(value = "车辆类型")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer carStyle;

    /**
     * 停车场地址
     */
    @ApiModelProperty(value = "停车场地址")
    private String parkingAddress;

    public String getParkingAddress() {
        return parkingAddress;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public String getRecordNo() {
        return recordNo;
    }

    public void setRecordNo(String recordNo) {
        this.recordNo = recordNo;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParkingLotCode() {
        return parkingLotCode;
    }

    public void setParkingLotCode(String parkingLotCode) {
        this.parkingLotCode = parkingLotCode;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(String parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(BigDecimal parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public String getParkingLength() {
        return this.parkingLength;
    }

    public void setParkingLength(String parkingLength) {
        this.parkingLength = parkingLength;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    public Boolean getPayable() {
        return payable;
    }

    public void setPayable(Boolean payable) {
        this.payable = payable;
    }

    public Boolean getNeedPay() {
        return needPay;
    }

    public void setNeedPay(Boolean needPay) {
        this.needPay = needPay;
    }

    public String getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(String payableAmount) {
        this.payableAmount = payableAmount;
    }

    public String getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(String actualPayAmount) {
        this.actualPayAmount = actualPayAmount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getPlateColor() {
        return plateColor;
    }

    public void setPlateColor(Integer plateColor) {
        this.plateColor = plateColor;
    }

    public Integer getCarStyle() {
        return carStyle;
    }

    public void setCarStyle(Integer carStyle) {
        this.carStyle = carStyle;
    }
}
