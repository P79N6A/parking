package com.zhuyitech.parking.pms.dto.request.vehicle;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 添加平台车辆请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
@ApiModel(value = "VehicleRecordAddRequestDto", description = "添加平台车辆请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordAddRequestDto extends SessionDto {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private String carStyle;

    /**
     * 车辆品牌
     */
    @ApiModelProperty("车辆品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty("车辆型号")
    private String carType;

    /**
     * 车辆颜色
     */
    @ApiModelProperty("车辆颜色")
    private Integer carColor;

    /**
     * 车辆等级
     */
    @ApiModelProperty("车辆等级")
    private Integer carLevel;

    /**
     * 车牌类型
     */
    @ApiModelProperty("车牌类型")
    private String plateType;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车主姓名
     */
    @ApiModelProperty("车主姓名")
    private String ownerName;

    /**
     * 车牌号(后几位字母)
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank
    private String plateNumber;

    /**
     * '车架号'
     */
    @ApiModelProperty("车架号")
    private String vehicleNumber;

    /**
     * 发动机号
     */
    @ApiModelProperty("发动机号")
    private String engineNumber;

    /**
     * 数据来源(系统OR设备)
     */
    @ApiModelProperty("dataOrigin")
    private String dataOrigin;

    /**
     * 校对状态
     */
    @ApiModelProperty("校对状态")
    private Boolean proofStatus;

}
