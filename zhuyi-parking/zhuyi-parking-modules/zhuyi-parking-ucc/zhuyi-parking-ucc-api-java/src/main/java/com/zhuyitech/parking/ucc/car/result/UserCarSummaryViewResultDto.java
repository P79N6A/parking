package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import com.scapegoat.infrastructure.jackson.annotation.SensitiveInfo;
import com.scapegoat.infrastructure.jackson.enums.SensitiveType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 用户车辆信息
 *
 * @author walkman
 */
@ApiModel(value = "UserCarSummaryViewResultDto", description = "用户车辆信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarSummaryViewResultDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

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
    @SensitiveInfo(SensitiveType.PLATE_NUMBER)
    private String plateNumber;

    /**
     * 认证状态
     */
    @ApiModelProperty(value = "认证状态(1 认证中 2 已认证 3认证不通过)")
    private Integer status;

    /**
     * 是否默认车辆
     */
    @ApiModelProperty(value = "是否默认车辆")
    private Boolean defaultCar;

}
