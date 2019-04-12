package com.zhuyitech.parking.tool.dto.result.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import com.zhuyitech.parking.tool.dto.result.traffic.TrafficRestrictionViewResultDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页天气和限号信息返回结果
 *
 * @author AkeemSuper
 * @date 2018/5/8 0008
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WeatherTrafficResultDto", description = "首页天气和限号信息返回结果")
public class WeatherTrafficResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "今日天气")
    private CurrentWeatherResultDto weather;

    @ApiModelProperty(value = "今日限行")
    private TrafficRestrictionViewResultDto trafficRestriction;

}
