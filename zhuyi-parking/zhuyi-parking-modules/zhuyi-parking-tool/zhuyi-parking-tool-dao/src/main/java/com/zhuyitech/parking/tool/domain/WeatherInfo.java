package com.zhuyitech.parking.tool.domain;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.scapegoat.infrastructure.core.entities.auditing.CreationAuditedEntity;

import java.util.Date;

/**
 * @author zwq
 */
@TableName("up_weather_info")
public class WeatherInfo extends CreationAuditedEntity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 区域编码
     */
    @TableField("adcode")
    private String adcode;

    /**
     * 温度
     */
    @TableField("temperature")
    private String temperature;

    /**
     * 天气现象（汉字描述）
     */
    @TableField("weather")
    private String weather;

    /**
     * 风向，风向编码对应描述
     */
    @TableField("winddirection")
    private String winddirection;

    /**
     * 风力，此处返回的是风力编码，风力编码对应风力级别，单位：级
     */
    @TableField("windpower")
    private String windpower;

    /**
     * 空气湿度
     */
    @TableField("humidity")
    private String humidity;

    /**
     * 数据发布的时间
     */
    @TableField("reporttime")
    private Date reporttime;

    /**
     * 预报日期
     */
    @TableField("forecastTime")
    private String forecastTime;

    /**
     * 白天天气现象
     */
    @TableField("dayweather")
    private String dayweather;

    /**
     * 晚上天气现象
     */
    @TableField("nightweather")
    private String nightweather;

    /**
     * 白天温度
     */
    @TableField("daytemp")
    private String daytemp;

    /**
     * 晚上温度
     */
    @TableField("nighttemp")
    private String nighttemp;

    /**
     * 白天风向
     */
    @TableField("daywind")
    private String daywind;

    /**
     * 晚上风向
     */
    @TableField("nightwind")
    private String nightwind;

    /**
     * 白天风力
     */
    @TableField("daypower")
    private String daypower;

    /**
     * 晚上风力
     */
    @TableField("nightpower")
    private String nightpower;

    /**
     * 天气类型(0实时1预报)
     */
    @TableField("reportType")
    private Integer reportType;

    /**
     * 创建者
     */
    @TableField(value = "creatorUserId", fill = FieldFill.INSERT)
    protected Long creatorUserId;

    /**
     * 创建日期
     */
    @TableField(value = "creationTime", fill = FieldFill.INSERT)
    protected Date creationTime;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getAdcode() {
        return adcode;
    }

    public void setAdcode(String adcode) {
        this.adcode = adcode;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    @Override
    public Long getCreatorUserId() {
        return creatorUserId;
    }

    @Override
    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getDayweather() {
        return dayweather;
    }

    public void setDayweather(String dayweather) {
        this.dayweather = dayweather;
    }

    public String getNightweather() {
        return nightweather;
    }

    public void setNightweather(String nightweather) {
        this.nightweather = nightweather;
    }

    public String getDaytemp() {
        return daytemp;
    }

    public void setDaytemp(String daytemp) {
        this.daytemp = daytemp;
    }

    public String getNighttemp() {
        return nighttemp;
    }

    public void setNighttemp(String nighttemp) {
        this.nighttemp = nighttemp;
    }

    public String getDaywind() {
        return daywind;
    }

    public void setDaywind(String daywind) {
        this.daywind = daywind;
    }

    public String getNightwind() {
        return nightwind;
    }

    public void setNightwind(String nightwind) {
        this.nightwind = nightwind;
    }

    public String getDaypower() {
        return daypower;
    }

    public void setDaypower(String daypower) {
        this.daypower = daypower;
    }

    public String getNightpower() {
        return nightpower;
    }

    public void setNightpower(String nightpower) {
        this.nightpower = nightpower;
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(String forecastTime) {
        this.forecastTime = forecastTime;
    }
}
