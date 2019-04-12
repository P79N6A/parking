package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(description = "用户是否绑定车辆返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserHasCarResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否有绑定车辆
     */
    @ApiModelProperty(value = "是否有绑定车辆")
    private Boolean hasCar;

    /**
     * 是否有认证通过车辆
     */
    @ApiModelProperty(value = "是否有认证通过车辆")
    private Boolean hasAuthenticatedCar;

}
