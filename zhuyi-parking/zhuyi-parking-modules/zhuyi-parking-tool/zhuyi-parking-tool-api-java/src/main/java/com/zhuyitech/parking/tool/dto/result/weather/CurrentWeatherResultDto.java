package com.zhuyitech.parking.tool.dto.result.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取用户天气信息返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CurrentWeatherResultDto", description = "获取用户天气信息返回结果")
public class CurrentWeatherResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 天气现象（汉字描述）
     */
    @ApiModelProperty(value = "天气现象（汉字描述）")
    private String weather;

    /**
     * 实时气温，单位：摄氏度
     */
    @ApiModelProperty(value = "实时气温，单位：摄氏度")
    private String temperature;

    /**
     * 最低温度
     */
    @ApiModelProperty(value = "最低温度")
    private String daytemp;

    /**
     * 最高温度
     */
    @ApiModelProperty(value = "最高温度")
    private String nighttemp;

    /**
     * 天气编号
     */
    @ApiModelProperty(value = "天气编号")
    private String weatherCode;

}
