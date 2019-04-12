package com.zhuyitech.parking.ucc.car.result;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 限行车辆信息
 *
 * @author yuzhicheng
 */
@ApiModel(value = "TrafficRestrictionCarInfoResultDto", description = "限行车辆信息")
@Data
@EqualsAndHashCode(callSuper = true)
public class TrafficRestrictionCarInfoResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌全号
     */
    @ApiModelProperty(value = "车牌全号", notes = "车牌全号")
    private String fullPlateNumber;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id", notes = "用户Id")
    private Long userId;

}
