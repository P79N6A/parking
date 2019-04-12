package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionCodeResultDto", description = "省市区Code视图模型")
public class RegionCodeResultDto extends BaseDto {

    private static final long serialVersionUID = 1L;

    /**
     * 省编码
     */
    @ApiModelProperty("provinceCode")
    private String provinceCode;

    /**
     * 市编码
     */
    @ApiModelProperty("cityCode")
    private String cityCode;

    /**
     * 区县编码
     */
    @ApiModelProperty("countyCode")
    private String countyCode;

}
