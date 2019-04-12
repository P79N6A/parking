package com.zhuyitech.parking.tool.dto.result.weather;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 逆地理获取adcode返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
public class WeatherInfoForecast implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 坐标点所在省名称
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域编码
     */
    private String adcode;

    /**
     * 数据发布的时间
     */
    private String reporttime;

    /**
     * 返回状态
     */
    private List<WeatherInfoCasts> casts;

}
