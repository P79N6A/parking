package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 获取车辆认证列表请求参数表
 *
 * @Date: 2018/1/12
 * @author: yuzhicheng
 */
@ApiModel(value = "UserCarAuthApplyListGetRequestDto", description = "获取车辆认证列表请求参数表")
public class UserCarInfoListGetRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌
     */
    @ApiModelProperty("品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty("车辆型号")
    private String carType;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 车架号
     */
    @ApiModelProperty("车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty("发动机号")
    private String engineNumber;

    /**
     * 申请日期
     */
    @ApiModelProperty("注册日期")
    private Date applyDateStart;

    /**
     * 申请日期
     */
    @ApiModelProperty("申请日期")
    private Date applyDateEnd;

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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public Date getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(Date applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public Date getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(Date applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }
}
