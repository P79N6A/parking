package com.zhuyitech.parking.ucc.dto.result.mock;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "CarAuthImageResultDto", description = "用户车辆绑定信息视图")
public class CarAuthImageResultDto extends EntityDto<Long> {

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
