package com.zoeeasy.cloud.tool.amap.dto.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 高德地图坐标转换结果
 *
 * @author walkman
 * @date 2018/4/27
 */

@ApiModel(value = "LocationConvertResultDto", description = "高德地图坐标转换结果")
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class LocationConvertResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true)
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true)
    private Double latitude;
}
