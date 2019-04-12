package com.zoeeasy.cloud.pms.specialvehicle.dto.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/10/26 0026
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SpecialVehicleTypeResultDto", description = "特殊车辆类型返回结果")
public class SpecialVehicleTypeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 特殊车辆类型
     */
    @ApiModelProperty("特殊车辆类型")
    private Integer specialType;
}
