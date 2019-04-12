package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author AkeemSuper
 * @date 2018/4/15 0015
 */
@ApiModel(value = "RegionGetByCityNameRequestDto", description = "根据城市名称获取地区请求参数")
public class RegionGetByCityNameRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    @ApiModelProperty("城市名称")
    private String cityName;

    /**
     * adcode
     */
    @ApiModelProperty("adCode")
    private String adCode;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
