package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionPagedRequestDto;
import com.zhuyitech.parking.common.constant.Const;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页查询车辆认证请求参数
 *
 * @Date: 2018/4/19
 * @author: AkeemSuper
 */
@ApiModel(value = "UserCarAuthQueryPageRequestDto", description = "分页查询车辆认证请求参数")
public class UserCarAuthQueryPageRequestDto extends SessionPagedRequestDto {

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
    private String fullPlateNumber;

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
     * 注册日期
     */
    @ApiModelProperty("注册日期")
    @DateTimeFormat(pattern = Const.FORMAT_DATE)
    private Date registerDate;

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer status;

    /**
     * 申请时间开始
     */
    @ApiModelProperty(value = "申请时间开始")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date applyTimeStart;

    /**
     * 申请时间结束
     */
    @ApiModelProperty(value = "申请时间结束")
    @DateTimeFormat(pattern = Const.FORMAT_DATETIME)
    private Date applyTimeEnd;

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

    public String getFullPlateNumber() {
        return fullPlateNumber;
    }

    public void setFullPlateNumber(String fullPlateNumber) {
        this.fullPlateNumber = fullPlateNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getApplyTimeStart() {
        return applyTimeStart;
    }

    public void setApplyTimeStart(Date applyTimeStart) {
        this.applyTimeStart = applyTimeStart;
    }

    public Date getApplyTimeEnd() {
        return applyTimeEnd;
    }

    public void setApplyTimeEnd(Date applyTimeEnd) {
        this.applyTimeEnd = applyTimeEnd;
    }
}
