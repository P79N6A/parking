package com.zoeeasy.cloud.tool.region.dto;

import com.scapegoat.infrastructure.core.dto.request.SessionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 获取上级区域列表请求参数
 *
 * @author AkeemSuper
 * @date 2018/5/10 0010
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegionGetUpperListRequestDto", description = "获取上级区域列表请求参数")
public class RegionGetUpperListRequestDto extends SessionDto {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "等级", required = true)
    @NotNull(message = "等级不能为空")
    private Integer level;

}
