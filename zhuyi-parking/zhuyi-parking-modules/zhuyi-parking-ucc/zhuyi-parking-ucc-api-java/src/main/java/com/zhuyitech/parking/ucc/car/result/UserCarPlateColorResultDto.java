package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户车颜色
 *
 * @author AkeemSuper
 * @date 2018/4/20 0020
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserCarPlateColorResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌颜色
     */
    @ApiModelProperty(dataType = "车牌颜色")
    private List<UserCarPlateColor> plateColor;

}
