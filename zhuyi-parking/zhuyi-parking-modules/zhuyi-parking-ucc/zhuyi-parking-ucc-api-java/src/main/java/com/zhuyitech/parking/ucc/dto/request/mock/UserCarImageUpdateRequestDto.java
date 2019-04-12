package com.zhuyitech.parking.ucc.dto.request.mock;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "UserCarImageUpdateRequestDto", description = "获取车辆logo列表分页请求参数表")
public class UserCarImageUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆图片URL
     */
    @ApiModelProperty(value = "车辆图片URL", notes = "车辆图片URL")
    private String carImageUrl;

    /**
     * 行驶证正页图片
     */
    @ApiModelProperty("行驶证正页图片")
    private String licensePhotoFront;

    /**
     * 行驶证副页图片
     */
    @ApiModelProperty("行驶证副页图片")
    private String licensePhotoContrary;

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
}
