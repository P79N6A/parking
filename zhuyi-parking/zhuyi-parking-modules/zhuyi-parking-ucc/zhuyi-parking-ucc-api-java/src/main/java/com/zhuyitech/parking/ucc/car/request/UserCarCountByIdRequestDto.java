package com.zhuyitech.parking.ucc.car.request;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 根据id查询用户车辆个数
 */
@ApiModel(value = "UserCarCountByIdRequestDto", description = "根据id查询用户车辆个数")
public class UserCarCountByIdRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
