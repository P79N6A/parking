package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.result.EntityDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 区域列表请求参数表
 *
 * @author walkman
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionListGetRequestDto", description = "区域列表请求参数表")
public class RegionListGetRequestDto extends EntityDto<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 地区代码
     */
    @ApiModelProperty("地区代码")
    private String code;

    /**
     * 地区名称
     */
    @ApiModelProperty("地区名称")
    private String name;

    /**
     * 上级ID
     */
    @ApiModelProperty("上级ID")
    private Long parentId;

    /**
     * 层级
     */
    @ApiModelProperty("层级")
    private Integer level;

    /**
     * 是否支持限行
     */
    @ApiModelProperty("是否支持限行")
    private Boolean trafficRestriction;

}