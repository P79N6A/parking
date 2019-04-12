package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户车辆认证信息添加请求参数
 *
 * @author yuzhicheng
 */
@ApiModel(value = "UserCarAuthAddRequestDto", description = "增加用户车辆认证请求参数")
public class UserCarInfoAddRequestDto extends SessionDto {

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
     * 车辆图片URL
     */
    @ApiModelProperty(value = "车辆图片URL", required = true)
    @NotBlank(message = "车辆图片URL不能为空")
    private String carImageUrl;

    public String getCarImageUrl() {
        return carImageUrl;
    }

    public void setCarImageUrl(String carImageUrl) {
        this.carImageUrl = carImageUrl;
    }

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
}
