package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import com.zhuyitech.parking.common.constant.Const;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改用户车辆认证请求参数
 *
 * @author yuzhicheng
 */
@ApiModel(value = "UserCarAuthUpdateRequestDto", description = "修改用户车辆认证请求参数")
public class UserCarInfoUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌", required = true)
    @NotBlank(message = "品牌不能为空")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty(value = "车辆型号", required = true)
    @NotBlank(message = "车型不能为空")
    private String carType;

    /**
     * 车牌归属地
     */
    @ApiModelProperty(value = "车牌归属地", required = true)
    @NotBlank(message = "车牌归属地不能为空")
    private String platePrefix;

    /**
     * 车牌首字母
     */
    @ApiModelProperty(value = "车牌首字母", required = true)
    @NotBlank(message = "车牌归属地不能为空")
    private String plateInitial;

    /**
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank(message = "车牌号不能为空")
    private String plateNumber;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(value = "车牌颜色", required = true)
    @NotNull(message = "车牌颜色不能为空")
    private Integer plateColor;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号", required = true)
    @NotBlank(message = "车架号不能为空")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号", required = true)
    @NotBlank(message = "发动机号不能为空")
    private String engineNumber;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期", required = true)
    @NotNull(message = "注册日期不能为空")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date registerDate;

    /**
     * 车辆图片URL
     */
    @ApiModelProperty(value = "车辆图片URL")
    private String carImageUrl;

    /**
     * 行驶证正页图片
     */
    @ApiModelProperty(value = "行驶证正页图片", required = true)
    @NotBlank(message = "行驶证正页图片不能为空")
    private String licensePhotoFront;

    /**
     * 行驶证副页图片
     */
    @ApiModelProperty(value = "行驶证副页图片")
    private String licensePhotoContrary;

    /**
     * 是否提醒限行
     */
    @ApiModelProperty("是否提醒限行")
    private Boolean hintTrafficLimit;

    /**
     * 是否提醒违章
     */
    @ApiModelProperty("是否提醒违章")
    private Boolean hintViolation;

    /**
     * 是否提醒年检
     */
    @ApiModelProperty("是否提醒年检")
    private Boolean hintYearCheck;

    /**
     * 默认车辆
     */
    @ApiModelProperty("默认车辆")
    private Boolean defaultCar;

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getPlatePrefix() {
        return platePrefix;
    }

    public void setPlatePrefix(String platePrefix) {
        this.platePrefix = platePrefix;
    }

    public String getPlateInitial() {
        return plateInitial;
    }

    public void setPlateInitial(String plateInitial) {
        this.plateInitial = plateInitial;
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

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

    public String getLicensePhotoFront() {
        return licensePhotoFront;
    }

    public void setLicensePhotoFront(String licensePhotoFront) {
        this.licensePhotoFront = licensePhotoFront;
    }

    public String getLicensePhotoContrary() {
        return licensePhotoContrary;
    }

    public void setLicensePhotoContrary(String licensePhotoContrary) {
        this.licensePhotoContrary = licensePhotoContrary;
    }

    public Boolean getHintTrafficLimit() {
        return hintTrafficLimit;
    }

    public void setHintTrafficLimit(Boolean hintTrafficLimit) {
        this.hintTrafficLimit = hintTrafficLimit;
    }

    public Boolean getHintViolation() {
        return hintViolation;
    }

    public void setHintViolation(Boolean hintViolation) {
        this.hintViolation = hintViolation;
    }

    public Boolean getHintYearCheck() {
        return hintYearCheck;
    }

    public void setHintYearCheck(Boolean hintYearCheck) {
        this.hintYearCheck = hintYearCheck;
    }

    public Boolean getDefaultCar() {
        return defaultCar;
    }

    public void setDefaultCar(Boolean defaultCar) {
        this.defaultCar = defaultCar;
    }
}
