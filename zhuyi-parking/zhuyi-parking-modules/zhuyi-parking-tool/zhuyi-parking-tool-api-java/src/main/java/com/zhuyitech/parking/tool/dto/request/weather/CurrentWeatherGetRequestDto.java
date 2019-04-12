package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取用户天气信息请求参数
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "CurrentWeatherGetRequestDto", description = "获取用户天气信息请求参数")
public class CurrentWeatherGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度")
    private Double latitude;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度")
    private Double longitude;

    /**
     * adCode
     */
    @ApiModelProperty(value = "adCode")
    private String adCode;

}
