package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 首页获取天气和限行信息的请求参数
 *
 * @author AkeemSuper
 * @date 2018/4/13 0013
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WeatherTrafficGetRequestDto", description = "首页获取天气和限行信息的请求参数")
public class WeatherTrafficGetRequestDto extends SessionDto {

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
