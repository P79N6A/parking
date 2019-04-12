package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import com.zhuyitech.parking.common.constant.Const;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户认证车辆请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@ApiModel(description = "UserCarAuthAddRequestDto")
public class UserCarAuthAddRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆Id
     */
    @ApiModelProperty(value = "车辆Id", required = true)
    @NotNull(message = "车辆Id不能为空")
    private Long carId;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "车架号", required = true)
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "发动机号", required = true)
    private String engineNumber;

    /**
     * 行驶证正面图片
     */
    @ApiModelProperty(value = "行驶证正面图片url", required = true)
    @NotBlank(message = "行驶证正面图片不能为空")
    private String licensePhotoFront;

    /**
     * 行驶证反面图片
     */
    @ApiModelProperty(value = "行驶证反面图片")
    private String licensePhotoContrary;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "注册日期", required = true)
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date registerDate;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
