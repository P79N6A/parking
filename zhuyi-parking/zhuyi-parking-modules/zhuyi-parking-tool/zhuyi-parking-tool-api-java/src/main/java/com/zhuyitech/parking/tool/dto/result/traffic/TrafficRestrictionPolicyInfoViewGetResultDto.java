package com.zhuyitech.parking.tool.dto.result.traffic;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import com.zhuyitech.parking.tool.dto.result.weather.WeatherResultDto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 限行返回结果视图
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionPolicyInfoViewGetResultDto", description = "限行返回结果视图")
public class TrafficRestrictionPolicyInfoViewGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称")
    private String cityName;

    /**
     * 城市编码
     */
    @ApiModelProperty(value = "城市编码")
    private String adCode;

    /**
     * 城市拼音
     */
    @ApiModelProperty(value = "城市拼音", example = "beijing")
    private String pinyinName;

    /**
     * 首字母
     */
    @ApiModelProperty(value = "首字母")
    private String initial;

    /**
     * 是否限行
     */
    @ApiModelProperty(value = "是否限行")
    private Integer trafficRestriction;

    /**
     * 天气
     */
    @ApiModelProperty(value = "天气")
    private WeatherResultDto weather;

    /**
     * 限行尾号
     */
    @ApiModelProperty(value = "限行尾号")
    private String restrictionTailNumber;

    /**
     * 限行政策
     */
    @ApiModelProperty(value = "限行政策")
    private List<TrafficRestrictionPolicyViewResultDto> policies;

}
