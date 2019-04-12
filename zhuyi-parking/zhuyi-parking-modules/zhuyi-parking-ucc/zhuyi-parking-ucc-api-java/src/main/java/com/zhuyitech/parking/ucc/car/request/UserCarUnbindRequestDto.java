package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author AkeemSuper
 * @date 2018/8/6 0006
 */
@ApiModel(value = "UserCarUnbindRequestDto", description = "判断用户车辆是否被解绑请求参数")
public class UserCarUnbindRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车辆Id
     */
    @ApiModelProperty(value = "车辆Id", required = true)
    @NotNull(message = "车辆id不能为空")
    private Long carId;

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }
}
