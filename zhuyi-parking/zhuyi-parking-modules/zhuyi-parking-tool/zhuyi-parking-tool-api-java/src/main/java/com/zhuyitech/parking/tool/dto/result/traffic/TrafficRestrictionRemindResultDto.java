package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author AkeemSuper
 * @date 2018/5/24 0024
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TrafficRestrictionRemindResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 车牌号
     */
    @ApiModelProperty("车牌号")
    private String fullPlateNumber;

    /**
     * 用户Id
     */
    @ApiModelProperty("用户Id")
    private Long userId;

}
