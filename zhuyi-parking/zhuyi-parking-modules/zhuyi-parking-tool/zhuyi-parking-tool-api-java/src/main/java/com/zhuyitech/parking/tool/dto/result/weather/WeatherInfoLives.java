package com.zhuyitech.parking.tool.dto.result.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * 逆地理获取adcode返回结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfoLives implements Serializable {

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
     * 天气现象（汉字描述）
     */
    private String weather;

    /**
     * 实时气温，单位：摄氏度
     */
    private String temperature;

    /**
     * 风向，风向编码对应描述
     */
    private String winddirection;

    /**
     * 风力，此处返回的是风力编码，风力编码对应风力级别，单位：级
     */
    private String windpower;

    /**
     * 空气湿度
     */
    private String humidity;

}
