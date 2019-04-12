package com.zhuyitech.parking.tool.dto.result.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 逆地理编码结果
 *
 * @author zwq
 * @date 2018-04-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "RegeoCodeGetResultDto", description = "获取天气返回结果")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegeoCodeGetResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 行政区编码
     */
    private String adCode;

}
