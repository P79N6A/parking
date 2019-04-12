package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 获取天气请求参数
 *
 * @author zwq
 * @date 2018/4/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WeatherPutInRequestDto", description = "获取天气请求参数")
public class WeatherPutInRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

}
