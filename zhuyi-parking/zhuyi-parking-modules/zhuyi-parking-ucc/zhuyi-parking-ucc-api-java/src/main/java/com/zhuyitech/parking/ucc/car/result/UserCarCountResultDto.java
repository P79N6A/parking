package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户车辆个数
 */
@ApiModel(value = "UserCarCountResultDto", description = "用户车辆个数")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarCountResultDto extends BaseDto {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户车辆个数
     */
    @ApiModelProperty(value = "用户车辆个数")
    private Integer count;

}
