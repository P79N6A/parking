package com.zhuyitech.parking.tool.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.zhuyitech.parking.tool.domain.WeatherInfo;
import com.zhuyitech.parking.tool.mapper.WeatherInfoMapper;
import com.zhuyitech.parking.tool.service.WeatherInfoCrudService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zwq
 * @date 2018-04-12
 */
@Service("weatherInfoCrudService")
public class WeatherInfoCrudServiceImpl extends ServiceImpl<WeatherInfoMapper, WeatherInfo> implements WeatherInfoCrudService {

    @Override
    public WeatherInfo selectCurrentWeather(String adcode, Integer reportType) {
        return baseMapper.selectCurrentWeather(adcode, reportType);
    }

    @Override
    public WeatherInfo selectForecastWeather(String adcode, Integer reportType, String forecastTime) {
        return baseMapper.selectForecastWeather(adcode, reportType, forecastTime);
    }

    @Override
    public void deleteWeatherBefore(Date reporttime) {
        baseMapper.deleteWeatherBefore(reporttime);
    }
}

