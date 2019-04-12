package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取区县列表请求参数
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "CountyListGetRequestDto", description = "获取区县列表请求参数")
public class CountyListGetRequestDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 市编码
     */
    @ApiModelProperty(value = "市编码")
    private String cityCode;

    /**
     * 区县编码
     */
    @ApiModelProperty(value = "区县编码")
    private String countyCode;

}