package com.zhuyitech.parking.tool.dto.result.weather;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 逆地理获取adcode返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
public class WeatherInfoCasts implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日期
     */
    private String date;

    /**
     * 星期几
     */
    private String week;

    /**
     * 白天天气现象
     */
    private String dayweather;

    /**
     * 晚上天气现象
     */
    private String nightweather;

    /**
     * 白天温度
     */
    private String daytemp;

    /**
     * 晚上温度
     */
    private String nighttemp;

    /**
     * 白天风向
     */
    private String daywind;

    /**
     * 晚上风向
     */
    private String nightwind;

    /**
     * 白天风力
     */
    private String daypower;

    /**
     * 晚上风力
     */
    private String nightpower;

}
