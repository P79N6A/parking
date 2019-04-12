package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 逆地理获取adcode请求参数
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GeocodeGetRequestDto", description = "逆地理获取adcode请求参数")
public class GeocodeGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 经纬度坐标(经纬度间以“,”分割)
     */
    @ApiModelProperty(value = "经纬度坐标", required = true)
    private String location;

}
