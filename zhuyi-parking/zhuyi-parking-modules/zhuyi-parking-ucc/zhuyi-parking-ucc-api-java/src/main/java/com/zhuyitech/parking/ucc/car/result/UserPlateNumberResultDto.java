package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户车牌信息
 *
 * @author zwq
 */
@ApiModel(value = "UserPlateNumberResultDto", description = "用户车牌信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserPlateNumberResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车型
     */
    @ApiModelProperty("车型")
    private Integer carStyle;

    /**
     * 车牌颜色
     */
    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String plateNumber;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "认证状态(1 认证中 2 已认证 3认证不通过)")
    private Integer status;

    /**
     * 品牌
     */
    @ApiModelProperty(value = "品牌", notes = "品牌")
    private String carBrand;

    /**
     * 车辆型号
     */
    @ApiModelProperty(value = "车辆型号", notes = "车辆型号")
    private String carType;

    /**
     * 是否默认车辆
     */
    @ApiModelProperty(value = "是否默认车辆")
    private Boolean defaultCar;

}
