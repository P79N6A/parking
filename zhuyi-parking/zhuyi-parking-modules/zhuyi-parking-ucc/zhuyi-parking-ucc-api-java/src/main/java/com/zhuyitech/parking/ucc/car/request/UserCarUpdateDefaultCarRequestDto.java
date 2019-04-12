package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户修改默认车辆的请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(description = "用户修改默认车辆的请求参数")
public class UserCarUpdateDefaultCarRequestDto extends SessionDto {
    private static final long serialVersionUID = 1L;

    /**
     * 车辆ID
     */
    @ApiModelProperty("车辆ID")
    private Long carId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
