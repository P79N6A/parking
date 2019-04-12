package com.zhuyitech.parking.tool.service;


import com.scapegoat.infrastructure.dataplus.service.CrudService;
import com.zhuyitech.parking.tool.domain.WeatherInfo;

import java.util.Date;

/**
 * @author zwq
 */
public interface WeatherInfoCrudService extends CrudService<WeatherInfo> {

    /**
     * 获取当前天气信息
     *
     * @param adcode,reportType
     * @return WeatherInfo
     */
    WeatherInfo selectCurrentWeather(String adcode, Integer reportType);

    /**
     * 获取预报天气信息
     *
     * @param adcode,reportType
     * @return WeatherInfo
     */
    WeatherInfo selectForecastWeather(String adcode, Integer reportType, String forecastTime);

    /**
     * 删除7天前
     *
     * @return WeatherInfo
     */
    void deleteWeatherBefore(Date reporttime);
}
