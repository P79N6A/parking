package com.zhuyitech.parking.tool.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.zhuyitech.parking.tool.domain.WeatherInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author zwq
 */
@Repository
public interface WeatherInfoMapper extends BaseMapper<WeatherInfo> {

    /**
     * 获取当前天气信息
     */
    WeatherInfo selectCurrentWeather(@Param("adcode") String adcode, @Param("reportType") Integer reportType);

    /**
     * 获取预报天气信息
     */
    WeatherInfo selectForecastWeather(@Param("adcode") String adcode, @Param("reportType") Integer reportType, @Param("forecastTime") String forecastTime);

    /**
     * 获取预报天气信息
     */
    void deleteWeatherBefore(@Param("reporttime") Date reporttime);
}
