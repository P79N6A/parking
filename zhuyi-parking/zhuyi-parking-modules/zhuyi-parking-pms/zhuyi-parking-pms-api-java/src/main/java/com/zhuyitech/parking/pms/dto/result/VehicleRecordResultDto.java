package com.zhuyitech.parking.pms.dto.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台车辆视图
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
@ApiModel(value = "VehicleRecordResultDto", description = "平台车辆视图")
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordResultDto extends EntityDto<Long> {

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
    private String carLevel;

    /**
     * '车牌类型'
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
    @ApiModelProperty("车牌号(后几位字母)")
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
