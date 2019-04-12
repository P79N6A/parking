package com.zhuyitech.parking.ucc.dto.result.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.common.utils.DateUtils;
import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import com.zhuyitech.parking.common.constant.Const;
import com.zhuyitech.parking.pms.dto.result.park.ParkingImageViewResultDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 用户停车记录详情视图模型
 * @Date: 2018/1/18 0018
 * @author: AkeemSuper
 */
@ApiModel(value = "UserParkingRecordDetailResultDto", description = "用户停车记录结果视图模型")
public class UserParkingRecordDetailResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 记录流水号
     */
    @ApiModelProperty(value = "记录流水号")
    private String recordNo;

    /**
     * 停车订单号
     */
    @ApiModelProperty(value = "停车订单号")
    private String orderNo;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATE, timezone = Const.TIMEZONE_GMT8)
    private Date parkingTime;

    /**
     * 停车开始时间
     */
    @ApiModelProperty(value = "停车开始时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date startTime;

    /**
     * 停车结束时间
     */
    @ApiModelProperty(value = "停车结束时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date endTime;

    /**
     * 停车时间
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
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    private BigDecimal payableAmount;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    private BigDecimal actualPayAmount;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Const.FORMAT_DATETIME, timezone = Const.TIMEZONE_GMT8)
    private Date payTime;

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
     * 停车图像URL列表
     */
    @ApiModelProperty(value = "停车图像URL列表")
    private List<ParkingImageViewResultDto> images;

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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Date getParkingTime() {
        return parkingTime;
    }

    public void setParkingTime(Date parkingTime) {
        this.parkingTime = parkingTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(BigDecimal parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public String getParkingLength() {

        if (startTime == null) {
            return "";
        }
        long timeMillis = 0;
        if (endTime == null) {
            timeMillis = DateUtils.now().getTime() - startTime.getTime();
        } else {
            timeMillis = endTime.getTime() - startTime.getTime();
        }
        return DateUtils.formatDateTimeChinese(timeMillis);
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

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public BigDecimal getActualPayAmount() {
        return actualPayAmount;
    }

    public void setActualPayAmount(BigDecimal actualPayAmount) {
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

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
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

    public List<ParkingImageViewResultDto> getImages() {
        return images;
    }

    public void setImages(List<ParkingImageViewResultDto> images) {
        this.images = images;
    }
}
