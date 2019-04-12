package com.zhuyitech.parking.tool.dto.request.trafficrestriction;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取限行城市列表的请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/18 0018
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TrafficRestrictionCityGetListRequestDto", description = "获取限行城市列表的请求参数")
public class TrafficRestrictionCityGetListRequestDto extends SessionDto {

    private static final long serialVersionUID = 1L;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "cityName")
    private String cityName;

    /**
     * 大写首字母
     */
    @ApiModelProperty("大写首字母")
    private String initial;

    /**
     * 车辆类型
     */
    @ApiModelProperty("车辆类型")
    private String carType;

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
