package com.zhuyitech.parking.tool.dto.request.weather;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "WeatherGetRequestDto", description = "获取天气请求参数")
public class WeatherGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * key
     */
    @ApiModelProperty(value = "key", hidden = true)
    private String key;

    /**
     * 城市adcode
     */
    @ApiModelProperty(value = "城市adcode", required = true)
    private String adcode;

    /**
     * 气象类型(base:返回实况天气/all:返回预报天气)
     */
    @ApiModelProperty(value = "气象类型(base:返回实况天气/all:返回预报天气)")
    private String extensions;

    /**
     * 返回格式
     */
    @ApiModelProperty(value = "返回格式", hidden = true)
    private String output;

}
