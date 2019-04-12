package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取子区域列表Dto
 *
 * @author Kane
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionGetChildListResultDto", description = "获取子区域列表Dto")
public class RegionGetChildListResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("子区域名称")
    private String name;

    @ApiModelProperty("子区域编码")
    private String code;

    @ApiModelProperty("子区域层级")
    private Integer level;

}
