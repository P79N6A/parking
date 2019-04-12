package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/8/6 0006
 */
@ApiModel(value = "UserCarUnbindResultDto", description = "用户车辆是否被解绑返回结果")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarUnbindResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 是否被解绑
     */
    @ApiModelProperty(value = "是否被解绑 true 已解绑 false 未解绑")
    private Boolean unbind;

}
