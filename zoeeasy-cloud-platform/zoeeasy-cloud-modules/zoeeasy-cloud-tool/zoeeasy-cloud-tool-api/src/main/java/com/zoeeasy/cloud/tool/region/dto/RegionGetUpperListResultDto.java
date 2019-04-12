package com.zoeeasy.cloud.tool.region.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.scapegoat.infrastructure.core.dto.basic.BaseDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 获取上级区域列表Dto
 *
 * @author AkeemSuper
 * @date 2018/5/10 0010
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionGetUpperListResultDto", description = "获取上级区域列表Dto")
public class RegionGetUpperListResultDto extends BaseDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Integer id;

    @ApiModelProperty("上级区域名称")
    private String name;

}
