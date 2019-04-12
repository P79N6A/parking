package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;


/**
 * 用户车辆违章查询
 *
 * @author walkman
 * @Date: 2018/4/14
 */
@ApiModel(value = "CarViolationQueryRequestDto", description = "用户车辆违章查询")
public class CarViolationQueryRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @ApiModelProperty(value = "车辆ID")
    @NotNull(message = "车辆ID不能为空")
    private Long carId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
