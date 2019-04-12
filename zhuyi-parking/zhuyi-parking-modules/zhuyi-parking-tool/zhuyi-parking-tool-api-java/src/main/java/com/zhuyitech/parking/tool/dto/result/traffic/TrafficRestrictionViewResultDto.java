package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页限行返回接口
 *
 * @author AkeemSuper
 * @date 2018/4/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("首页限行返回接口")
public class TrafficRestrictionViewResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 今日限行尾号
     */
    @ApiModelProperty("今日限行尾号")
    private String tailNumber;

    /**
     * 明日限行尾号
     */
    @ApiModelProperty("明日限行尾号")
    private String tomorrowTailNumber;

    /**
     * 当前城市
     */
    @ApiModelProperty("当前城市")
    private String cityName;

    /**
     * 今日是否限行状态
     */
    @ApiModelProperty("今日是否限行状态")
    private Integer trafficRestriction;

    /**
     * 明日是否限行状态
     */
    @ApiModelProperty("明日是否限行状态")
    private Integer tomorrowTrafficRestriction;

}
