package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;
import com.zhuyitech.parking.common.constant.Const;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 车辆申请审核请求参数
 *
 * @author AkeemSuper
 */
@ApiModel(value = "UserCarApproveRequestDto", description = "车辆申请审核请求参数")
public class UserCarAuthApproveRequestDto extends SessionEntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 审核意见 1: 同意 2 拒绝
     */
    @ApiModelProperty(required = true, notes = "审核意见 1: 同意 2 拒绝")
    @NotNull(message = "审核意见不能为空")
    private Integer approveOpinion;

    /**
     * 说明,审核拒绝必填
     */
    @ApiModelProperty(notes = "说明,审核拒绝必填")
    private String remarks;

    /**
     * 车辆颜色
     */
    @ApiModelProperty(notes = "车辆颜色")
    private Integer carColor;

    /**
     * 车辆类型
     */
    @ApiModelProperty(notes = "车辆类型")
    private Integer carLevel;

    /**
     * 车牌类型
     */
    @ApiModelProperty(notes = "车牌类型")
    private String plateType;

    /**
     *车架号
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
    @DateTimeFormat(pattern = Const.FORMAT_DATE)    private Date registerDate;
    public Integer getApproveOpinion() {
        return approveOpinion;
    }

    public void setApproveOpinion(Integer approveOpinion) {
        this.approveOpinion = approveOpinion;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getCarColor() {
        return carColor;
    }

    public void setCarColor(Integer carColor) {
        this.carColor = carColor;
    }

    public Integer getCarLevel() {
        return carLevel;
    }

    public void setCarLevel(Integer carLevel) {
        this.carLevel = carLevel;
    }

    public String getPlateType() {
        return plateType;
    }

    public void setPlateType(String plateType) {
        this.plateType = plateType;
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
}
