package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 获取区县列表请求参数
 *
 * @author walkman
 */
@ApiModel(value = "CountyListGetRequestDto", description = "获取区县列表请求参数")
public class CountyListGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String cityCode;

    /**
     * 区县编码
     */
    @ApiModelProperty(value = "省编码")
    private String countyCode;

    public String getCountyCode() {
        return countyCode;
    }

    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }
}