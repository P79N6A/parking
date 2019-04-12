package com.zhuyitech.parking.tool.dto.request.region;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import org.hibernate.validator.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通过市name获取adCode
 *
 * @author zwq
 */
@ApiModel(value = "RegionProvinceAdCodeGetRequestDto", description = "通过市name获取adCode")
public class RegionProvinceAdCodeGetRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 市名称
     */
    @ApiModelProperty(value = "市名称",required = true)
    @NotBlank
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
