package com.zoeeasy.cloud.tool.amap.dto.request;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * 高德地图坐标转换请求参数
 *
 * @author walkman
 * @date 2018/4/27
 */
@Data
@ApiModel(value = "LocationConvertRequestDto", description = "高德地图坐标转换请求参数")
@EqualsAndHashCode(callSuper = false)
public class LocationConvertRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private Double longitude;

    /**
     * 纬度
     */
    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private Double latitude;

    @ApiModelProperty(value = "原坐标系", required = true, allowableValues = "gps,mapbar,baidu,autonavi")
    @NotEmpty(message = "原坐标系不能为空")
    private String coordSys;
}

