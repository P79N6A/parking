package com.zhuyitech.parking.tool.service.api;

import com.scapegoat.infrastructure.core.dto.result.ObjectResultDto;
import com.scapegoat.infrastructure.core.dto.result.ResultDto;
import com.zhuyitech.parking.tool.dto.request.AdCodeRequestDto;
import com.zhuyitech.parking.tool.dto.request.weather.*;
import com.zhuyitech.parking.tool.dto.result.weather.CurrentWeatherResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.WeatherGetResultDto;
import com.zhuyitech.parking.tool.dto.result.weather.WeatherResultDto;

/**
 * WeatherService
 *
 * @author zwq
 */
public interface WeatherService {

    /**
     * 获取城市天气
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<WeatherGetResultDto> weatherGet(WeatherGetRequestDto requestDto);

    /**
     * 获取用户所在地区的天气信息
     *
     * @param requestDto
     * @return
     */
    ObjectResultDto<CurrentWeatherResultDto> getCurrentWeather(AdCodeRequestDto requestDto);

    /**
     * 实况天气信息入库
     *
     * @param requestDto
     * @return
     */
    ResultDto weatherLivesPutIn(WeatherPutInRequestDto requestDto);

    /**
     * 预报天气信息入库
     *
     * @param requestDto
     * @return
     */
    ResultDto weatherForecastsPutIn(WeatherPutInRequestDto requestDto);

    /**
     * 预报天气后三天信息入库
     *
     * @param requestDto
     * @return
     */
    ResultDto weatherForecasts(WeatherPutInRequestDto requestDto);

    /**
     * 获取指定城市指定日期的天气
     * @param requestDto WeatherGetByDateRequestDto
     * @return WeatherResultDto
     */
    ObjectResultDto<WeatherResultDto> getWeatherByDate(WeatherGetByDateRequestDto requestDto);

    /**
     * 预报天气后三天信息入库
     *
     * @return
     */
    ResultDto deleteWeatherBefore(WeatherDeleteRequestDto requestDto);

}
