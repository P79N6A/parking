package com.zhuyitech.parking.tool.dto.result.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 获取天气返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WeatherGetResultDto", description = "获取天气返回结果")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 返回状态
     */
    private String status;

    /**
     * 返回状态
     */
    private Integer count;

    /**
     * 返回状态
     */
    private String info;

    /**
     * 返回状态
     */
    private String infocode;

    /**
     * 返回状态
     */
    private List<WeatherInfoLives> lives;

    /**
     * 返回状态
     */
    private List<WeatherInfoForecast> forecasts;

}
