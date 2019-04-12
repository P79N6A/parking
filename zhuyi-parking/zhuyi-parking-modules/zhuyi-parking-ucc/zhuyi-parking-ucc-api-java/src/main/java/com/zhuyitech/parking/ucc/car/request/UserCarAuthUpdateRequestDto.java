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
 * 用户认证车辆修改请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/19 0019
 */
@ApiModel(description = "UserCarAuthUpdateRequestDto")
public class UserCarAuthUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车架号
     */
    @ApiModelProperty(value = "vehicleNumber", required = true)
    @NotBlank(message = "车架号不能为空")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty(value = "engineNumber", required = true)
    @NotBlank(message = "发动机号不能为空")
    private String engineNumber;

    /**
     * 行驶证正面图片
     */
    @ApiModelProperty(value = "licensePhotoFront", required = true)
    @NotBlank(message = "行驶证正面图片不能为空")
    private String licensePhotoFront;

    /**
     * 行驶证反面图片
     */
    @ApiModelProperty(value = "licensePhotoContrary")
    private String licensePhotoContrary;

    /**
     * 注册日期
     */
    @ApiModelProperty(value = "registerDate", required = true)
    @NotNull(message = "注册日期不能为空")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date registerDate;

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
