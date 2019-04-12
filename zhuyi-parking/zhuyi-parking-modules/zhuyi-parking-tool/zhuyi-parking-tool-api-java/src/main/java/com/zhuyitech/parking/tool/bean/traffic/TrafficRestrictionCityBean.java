package com.zhuyitech.parking.tool.bean.traffic;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 限行城市实体类
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
public class TrafficRestrictionCityBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市代码
     */
    @ApiModelProperty("城市代码")
    private String city;

    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityname;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
}
