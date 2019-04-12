package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TreeResultDto", description = "树参数")
public class TreeResultDto extends BaseDto {
    /**
     * 简称
     */
    @ApiModelProperty(value = "区域名称")
    private String name;

    /**
     * 编号
     */
    @ApiModelProperty(value = "区域编号")
    private String code;

    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer level;

    /**
     * 下级列表
     */
    @ApiModelProperty(value = "下级列表")
    private List<TreeResultDto> child;
}
