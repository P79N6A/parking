package com.zhuyitech.parking.ucc.dto.request.mock;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "CarAuthImageUpdateRequestDto", description = "获取车辆logo列表分页请求参数表")
public class CarAuthImageUpdateRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

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