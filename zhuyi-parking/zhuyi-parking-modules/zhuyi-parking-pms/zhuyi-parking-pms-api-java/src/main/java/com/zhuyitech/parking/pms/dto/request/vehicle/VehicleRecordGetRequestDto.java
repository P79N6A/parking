package com.zhuyitech.parking.pms.dto.request.vehicle;

import com.scapegoat.infrastructure.core.dto.request.SessionEntityDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改平台车辆请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/16 0016
 */
@ApiModel(value = "VehicleRecordGetRequestDto", description = "获取平台车辆请求参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class VehicleRecordGetRequestDto extends SessionEntityDto<Long> {

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
     * 车牌号
     */
    @ApiModelProperty(value = "车牌号", required = true)
    @NotBlank
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
     * 校对状态
     */
    @ApiModelProperty("校对状态")
    private Boolean proofStatus;

}
