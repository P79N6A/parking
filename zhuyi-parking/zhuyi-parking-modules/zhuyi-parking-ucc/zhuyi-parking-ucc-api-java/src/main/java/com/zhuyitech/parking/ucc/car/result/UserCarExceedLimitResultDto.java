package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户绑定车辆是否超过限定数量
 *
 * @author AkeemSuper
 * @date 2018/4/17 0017
 */
@ApiModel(description = "用户绑定车辆是否超过限定数量")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarExceedLimitResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否有绑定车辆
     */
    private Boolean exceed;

}
